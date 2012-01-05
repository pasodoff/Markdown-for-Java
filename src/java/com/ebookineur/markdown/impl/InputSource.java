package com.ebookineur.markdown.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class InputSource {
	private final BufferedReader _br;
	private final List<Integer> _linenosToSkip;
	private int _lineno;

	InputSource(File input, List<Integer> linenosToSkip) throws IOException {
		_linenosToSkip = linenosToSkip;
		_br = new BufferedReader(new FileReader(input));
		_lineno = 0;
	}

	void close() throws IOException {
		_br.close();
	}

	List<String> getPara() throws IOException {
		List<String> result = new ArrayList<String>();
		while (true) {
			String line = _br.readLine();
			_lineno++;
			if (line == null) {
				// end of file reached
				if (result.size() > 0) {
					return result;
				} else {
					return null;
				}
			}
			
			// check if the linke has to be skipped
			if (_linenosToSkip.contains(_lineno)) {
				continue;
			}

			if (line.trim().length() == 0) {
				// empty line
				if (result.size() > 0) {
					return result;
				}
			} else {
				result.add(line);
			}
		}
	}
}