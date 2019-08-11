package com.schneide.abas.ccd.red.refactoring.two;

import com.schneide.abas.ccd.red.refactoring.two.rotfo.RamsesProcess;
import com.schneide.abas.ccd.red.refactoring.two.rotfo.ArrayUtil;
import com.schneide.abas.ccd.red.refactoring.two.rotfo.DefaultDialog;
import com.schneide.abas.ccd.red.refactoring.two.rotfo.DelphinChannelSpecificationDialog;
import com.schneide.abas.ccd.red.refactoring.two.rotfo.DelphinValueDevelopingDialog;
import com.schneide.abas.ccd.red.refactoring.two.rotfo.DelphinValueProvider;
import com.schneide.abas.ccd.red.refactoring.two.rotfo.DialogCloseListener;
import com.schneide.abas.ccd.red.refactoring.two.rotfo.GenericDelphinChannelReference;
import com.schneide.abas.ccd.red.refactoring.two.rotfo.I18NKey;
import com.schneide.abas.ccd.red.refactoring.two.rotfo.RamsesStationManager;
import com.schneide.abas.ccd.red.refactoring.two.rotfo.RamsesStationManagerUtil;
import com.schneide.abas.ccd.red.refactoring.two.rotfo.SchneideDialog;

public class SomeFunctionality extends RamsesProcess {

	public void activate(final DelphinValueProvider valueProvider, final RamsesStationManager stationManager) {
	    if (ArrayUtil.isEmptyOrNull(stationManager.getAllStations())) {
	        DefaultDialog.showWarningDialog(getSession().getMainWindowStack(),
	                new I18NKey("warning.no.stations")); //$NON-NLS-1$
	        return;
	    }
	    DelphinChannelSpecificationDialog channelDialog = new DelphinChannelSpecificationDialog(
	            getSession(), RamsesStationManagerUtil.getAllStationIdentifiersFor(stationManager));
	    channelDialog.showDialog();
	    if (channelDialog.wasClosedByCancel()) {
	        return;
	    }

	    final DelphinValueDevelopingDialog dialog = new DelphinValueDevelopingDialog(
	            getSession(),
	            new GenericDelphinChannelReference(
	                    channelDialog.getSelectedStation(),
	                    channelDialog.getChannelName())
	    );
	    valueProvider.addDelphinValueListener(dialog);
	    dialog.showDialog();
	    dialog.addCloseListener(new DialogCloseListener() {
	        @Override
	        public void dialogClosed(SchneideDialog schneideDialog) {
	            valueProvider.removeDelphinValueListener(dialog);
	        }
	    });
	}
}
