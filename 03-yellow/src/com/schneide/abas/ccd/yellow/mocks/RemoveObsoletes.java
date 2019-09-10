package com.schneide.abas.ccd.yellow.mocks;

import java.io.File;

public class RemoveObsoletes {

	public RemoveObsoletes() {
		super();
	}

	public void file(File candidate) {
		if (!candidate.isFile()) {
			return;
		}
		if (0L == candidate.length()) {
			candidate.delete();
			return;
		}
		if (candidate.getName().endsWith(".bak")) {
			candidate.delete();
			return;
		}
		if (candidate.lastModified() < (System.currentTimeMillis() - 604_800_000L)) {
			candidate.delete();
			return;
		}
	}
}
