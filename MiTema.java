import com.formdev.flatlaf.themes.FlatMacLightLaf;

public class MiTema
	extends FlatMacLightLaf
{
	public static final String NAME = "MiTema";

	public static boolean setup() {
		return setup( new MiTema() );
	}

	public static void installLafInfo() {
		installLafInfo( NAME, MiTema.class );
	}

	@Override
	public String getName() {
		return NAME;
	}
}
