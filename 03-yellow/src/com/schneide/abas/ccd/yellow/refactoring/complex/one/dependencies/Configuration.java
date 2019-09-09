package com.schneide.abas.ccd.yellow.refactoring.complex.one.dependencies;

public interface Configuration {

	PackageManager getPackageManager();

	OldPackageManager getOldPackageManager();

	ImportServiceDao getImportServiceDao();

}
