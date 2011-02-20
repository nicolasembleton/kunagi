/*
 * Copyright 2011 Witoslaw Koczewsi <wi@koczewski.de>, Artjom Kochtchi
 * 
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package scrum.server.collaboration;

import ilarkesto.pdf.APdfContainerElement;
import scrum.server.common.APdfCreator;
import scrum.server.common.ScrumPdfContext;
import scrum.server.common.WikiToPdfConverter;

public class WikipagePdfCreator extends APdfCreator {

	private Wikipage wikipage;

	public WikipagePdfCreator(Wikipage wikipage) {
		super();
		this.wikipage = wikipage;
	}

	@Override
	protected void build(APdfContainerElement pdf) {
		WikiToPdfConverter.buildPdf(pdf, wikipage.getText(), new ScrumPdfContext());
	}

	@Override
	protected String getFilename() {
		return wikipage.getName();
	}

}
