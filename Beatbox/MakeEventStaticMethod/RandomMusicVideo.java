import javax.sound.midi.*;

class RandomMusicVideo{

	public static void main(String[] args){
			RandomMusicVideo video = new RandomMusicVideo();
			video.play();
	}

	public void play(){
		try{
			Sequencer player = MidiSystem.getSequencer();
			player.open();
			Sequence seq =new Sequence(Sequence.PPQ, 4);
			Track track = seq.createTrack();

			//first.setMessage(ShortMessage.PROGRAM_CHANGE,1,instrument,0);

			for(int i=5;i<61;i+=4){
				System.out.println("Looping");
				track.add(makeEvent(144,1,i,100,i));
				track.add(makeEvent(128,1,i,100,i+2));
			}

			/*ShortMessage a = new ShortMessage();
			a.setMessage(144,1,44,100);
			MidiEvent noteOn = new MidiEvent(a,1);
			track.add(noteOn);

			ShortMessage b = new ShortMessage();
			b.setMessage(128,1,44,100);
			MidiEvent noteOff = new MidiEvent(b,16);
			track.add(noteOff);*/

			player.setSequence(seq);
			player.setTempoInBPM(220);
			player.start();
		}catch(MidiUnavailableException e){
			System.out.println("Oops no sequencer.");
			e.printStackTrace();
		}catch(Exception ex){
			System.out.println("Got a different exception than MidiUnavailable");
			ex.printStackTrace();
		}
		
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

		/*try{
		ShortMessage a = new ShortMessage();
		a.setMessage(144,1,44,100);
		event = new MidiEvent(a,1);
		}catch(Exception e){}*/
		
		return event;
	}
}