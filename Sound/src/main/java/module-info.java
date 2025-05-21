import dk.sdu.mmmi.cbse.SoundService;
import dk.sdu.mmmi.cbse.common.services.ISoundService;

module Sound {
    requires Common;
    requires javafx.media;
    opens audios;
    provides ISoundService with SoundService;
}