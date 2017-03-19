package SpaceAgeWars.CustomUtils;

import com.jogamp.openal.AL;
import com.jogamp.openal.ALFactory;
import com.jogamp.openal.util.WAVData;
import com.jogamp.openal.util.WAVLoader;

import java.io.IOException;
import java.util.HashMap;

@SuppressWarnings("unused")
public class AudioController {
	private AL al = ALFactory.getAL();
	private HashMap<String, WAVData> sounds = new HashMap<String, WAVData>();

	public boolean addSound(String name, String path) throws IOException {
		if (sounds.containsKey(name)) return false;
		sounds.put(name, WAVLoader.loadFromFile(path));
		return true;
	}
}
