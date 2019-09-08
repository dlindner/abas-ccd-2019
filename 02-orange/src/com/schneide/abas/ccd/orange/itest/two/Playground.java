package com.schneide.abas.ccd.orange.itest.two;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.schneide.abas.ccd.orange.itest.two.WebBasedClickMeGame.RandomnessSource;
import com.schneide.abas.ccd.orange.soc.one.util.swing.EDT;

public class Playground {

	private final List<JButton> targets;
	private final JFrame visualization;
	private final JPanel targetField;
	private final JLabel points;
	private final JLabel remainingTargets;
	private final RandomnessSource rng;
	private int removedTargets;
	private int targetCounter;

	public Playground(RandomnessSource rng) {
		super();
		this.removedTargets = 0;
		this.targetCounter = 0;
		this.rng = rng;
		this.targets = new LinkedList<>();
		this.targetField = EDT.query(() -> {
			JPanel result = new JPanel(null);
			result.setName("game field");
			result.setSize(400, 400);
			result.setPreferredSize(new Dimension(400, 400));
			result.setMaximumSize(new Dimension(400, 400));
			result.setMinimumSize(new Dimension(400, 400));
			return result;
		});
		this.points = EDT.query(() -> bigVisibleLabel("points"));
		this.remainingTargets = EDT.query(() -> bigVisibleLabel("targets"));
		this.visualization = EDT.query(() -> {
			JFrame result = new JFrame("Click me!");
			result.setName("game frame");
			result.setResizable(false);
			result.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			result.getContentPane().add(this.targetField, BorderLayout.CENTER);
			JPanel state = new JPanel(new BorderLayout());
			state.add(this.points, BorderLayout.WEST);
			state.add(this.remainingTargets, BorderLayout.EAST);
			result.getContentPane().add(state, BorderLayout.SOUTH);
			result.pack();
			return result;
		});
	}

	private JLabel bigVisibleLabel(String name) {
		JLabel result = new JLabel("0");
		result.setName(name);
		result.setFont(result.getFont().deriveFont(48.0f));
		result.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 25));
		return result;
	}

	public void start(final boolean looping) {
		EDT.performBlocking(() -> {
			this.visualization.setVisible(true);
		});
		Thread fun = new Thread(() -> {
			do {
				addTarget();
				try {
					Thread.sleep(rng.randomInteger(1000));
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			} while (looping);
		}, "target creator");
		fun.start();
	}

	private void addTarget() {
		synchronized (this.targets) {
			EDT.performBlocking(() -> {
				final JButton newClick = randomButton();
				newClick.setLocation(
						this.rng.randomInteger(400 - newClick.getWidth()),
						this.rng.randomInteger(400 - newClick.getHeight()));
				System.out.println(newClick.getLocation());
				this.targets.add(newClick);
				this.targetField.add(newClick);
			});
		}
		updateState();
		EDT.perform(this.visualization::repaint);
	}

	private void updateState() {
		EDT.perform(() -> this.points.setText(String.valueOf(this.removedTargets)));
		EDT.perform(() -> this.remainingTargets.setText(String.valueOf(this.targets.size())));
	}

	private JButton randomButton() {
		final JButton result = new JButton("Click me!");
		result.setName("target-" + this.targetCounter);
		this.targetCounter++;
		result.setSize(
				this.rng.randomInteger(100) + 10,
				this.rng.randomInteger(50) + 5);
		result.addActionListener(e -> {
			this.targetField.remove(result);
			synchronized (this.targets) {
				this.targets.remove(result);
			}
			this.removedTargets++;
			updateState();
		});
		return result;
	}
}
