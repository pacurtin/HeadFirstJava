import javax.sound.midi.*;

class RandomMusicVideo implements ControllerEventListener{

	public static void main(String[] args){
			RandomMusicVideo video = new RandomMusicVideo();
			video.play();
	}

	public void play(){
		try{
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.open();

			int[] eventsIWant={127};
			sequencer.addControllerEventListener(this, eventsIWant);

			Sequence seq =new Sequence(Sequence.PPQ, 4);
			Track track = seq.createTrack();

			//first.setMessage(ShortMessage.PROGRAM_CHANGE,1,instrument,0);

			for(int i=5;i<61;i+=4){
				track.add(makeEvent(144,1,i,100,i));	//144=SHORTMESSAGE.NOTE_ON
				track.add(makeEvent(176,1,127,0,i));	//176=SHORTMESSAGE.CONTROLLER_EVENT
				//By listening for this event we can trigger things in time to the music.
				track.add(makeEvent(128,1,i,100,i+2));	//128=SHORTMESSAGE.NOTE_OFF
			}

			//This is how we can manually enter a note. Useful for debugging.
			/*ShortMessage a = new ShortMessage();
			a.setMessage(144,1,44,100);
			MidiEvent noteOn = new MidiEvent(a,1);
			track.add(noteOn);

			ShortMessage b = new ShortMessage();
			b.setMessage(128,1,44,100);
			MidiEvent noteOff = new MidiEvent(b,16);
			track.add(noteOff);*/
			
			sequencer.setSequence(seq);
			sequencer.setTempoInBPM(220);
			sequencer.start();
		}catch(MidiUnavailableException e){
			System.out.println("Oops no sequencer.");
			e.printStackTrace();
		}catch(Exception ex){
			System.out.println("Got a different exception than MidiUnavailable");
			ex.printStackTrace();
		}
		
	}	

	public void controlChange(ShortMessage event){
		System.out.println("la");	//Should print la in time to the beat.
	}

	public static MidiEvent makeEvent(int comd, int chan, int one, int two, int tick){
		MidiEvent event = null;
		try{
			ShortMessage a = new ShortMessage();
			System.out.println("Comd:"+comd+" Chan:"+chan+" One:"+one+" Two:"+two+" Tick:"+tick);
			a.setMessage(comd,chan,one,two);
			event = new MidiEvent(a,tick);
		}catch(Exception e){
			System.out.println("Problem");
		}
		
		return event;
	}
}