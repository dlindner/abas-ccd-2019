package com.schneide.abas.ccd.yellow.mocks;

import java.io.File;

import org.junit.Test;
import org.mockito.Mockito;

public class RemoveObsoletesTests {

	@Test
	public void removesOldFiles() throws Exception {
		File oldFile = Mockito.mock(File.class);
		Mockito.when(oldFile.isFile()).thenReturn(true);
		Mockito.when(oldFile.length()).thenReturn(1L);
		Mockito.when(oldFile.getName()).thenReturn("tested.txt");

		Mockito.when(oldFile.lastModified()).thenReturn(System.currentTimeMillis() - 1_000_000_000L);
		Mockito.when(oldFile.delete()).thenReturn(true);

		RemoveObsoletes target = new RemoveObsoletes();
		target.file(oldFile);

		Mockito.verify(oldFile).delete();
	}

	@Test
	public void sparesRecentFiles() throws Exception {
		File oldFile = Mockito.mock(File.class);
		Mockito.when(oldFile.isFile()).thenReturn(true);
		Mockito.when(oldFile.length()).thenReturn(1L);
		Mockito.when(oldFile.getName()).thenReturn("tested.txt");

		Mockito.when(oldFile.lastModified()).thenReturn(System.currentTimeMillis() - 1_000_000L);
		Mockito.when(oldFile.delete()).thenReturn(true);

		RemoveObsoletes target = new RemoveObsoletes();
		target.file(oldFile);

		Mockito.verify(oldFile, Mockito.never()).delete();
	}
}
