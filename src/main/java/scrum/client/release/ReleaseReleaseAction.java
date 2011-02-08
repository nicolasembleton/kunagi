package scrum.client.release;

import ilarkesto.gwt.client.Date;
import scrum.client.common.TooltipBuilder;

public class ReleaseReleaseAction extends GReleaseReleaseAction {

	public ReleaseReleaseAction(scrum.client.release.Release release) {
		super(release);
	}

	@Override
	public String getLabel() {
		return "Mark as published";
	}

	@Override
	public String getTooltip() {
		TooltipBuilder tb = new TooltipBuilder("Mark this release as published and available to the users.");

		if (!getCurrentProject().isScrumTeamMember(getCurrentUser())) tb.addRemark(TooltipBuilder.NOT_SCRUMTEAM);

		return tb.getTooltip();
	}

	@Override
	public boolean isPermitted() {
		if (!release.getProject().isScrumTeamMember(getCurrentUser())) return false;
		return true;
	}

	@Override
	public boolean isExecutable() {
		if (release.isReleased()) return false;
		return true;
	}

	@Override
	protected void onExecute() {
		Date previousDate = release.getReleaseDate();
		release.setReleaseDate(Date.today());

		release.setReleaseNotes(release.createIzemizedReleaseNotes());

		release.setReleased(true);
		addUndo(new Undo(previousDate));
	}

	class Undo extends ALocalUndo {

		private Date date;

		public Undo(Date date) {
			super();
			this.date = date;
		}

		@Override
		public String getLabel() {
			return "Undo Mark as published " + release.getReference() + " " + release.getLabel();
		}

		@Override
		protected void onUndo() {
			release.setReleaseDate(date);
			release.setReleased(false);
		}

	}

}