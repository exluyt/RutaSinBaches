package Design;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

/**
 * Custom Look and Feel theme extending FlatMacLightLaf. This class defines a
 * custom theme named "MiTema" which is based on FlatLaf's FlatMacLightLaf.
 * 
 * @author Henry
 */
public class MiTema extends FlatMacLightLaf {
	/**
	 * The name of the custom theme.
	 */
	public static final String NAME = "MiTema";

	/**
	 * Sets up the custom theme.
	 * 
	 * @return true if the theme was set up successfully, false otherwise
	 */
	public static boolean setup() {
		return setup(new MiTema());
	}

	/**
	 * Installs the Look and Feel information for this custom theme.
	 */
	public static void installLafInfo() {
		installLafInfo(NAME, MiTema.class);
	}

	/**
	 * Returns the name of the custom theme.
	 * 
	 * @return the name of the custom theme
	 */
	@Override
	public String getName() {
		return NAME;
	}
}
