import javax.sound.midi.*;

class MiniMusicMakerApp{
	public void play(int instrument, int note){
		try{
			Sequencer player = MidiSystem.getSequencer();
			player.open();
			Sequence seq =new Sequence(Sequence.PPQ, 4);
			Track track = seq.createTrack();

			ShortMessage first = new ShortMessage();
			first.setMessage(ShortMessage.PROGRAM_CHANGE,1,instrument,0);
			MidiEvent changeInstrument = new MidiEvent(first,1);
			track.add(changeInstrument); 

			ShortMessage a = new ShortMessage();
			a.setMessage(ShortMessage.NOTE_ON,1,note,100);
			MidiEvent noteOn = new MidiEvent(a,1);
			track.add(noteOn);

			ShortMessage b = new ShortMessage();
			b.setMessage(ShortMessage.NOTE_OFF,1,note,100);
			MidiEvent noteOff = new MidiEvent(b,16);
			track.add(noteOff);

			player.setSequence(seq);
			player.start();
			//System.out.println("Shows over.");
		}catch(MidiUnavailableException e){
			System.out.println("Oops no sequencer.");
			e.printStackTrace();
		}catch(Exception ex){
			System.out.println("Got a different exception than MidiUnavailable");
			ex.printStackTrace();
		}
		
	}	

	public static void main(String[] args){
		if(args.length<2){
			System.out.println("Don't forget the arguments.");
		}else{
			try{
			MiniMusicMakerApp musicMaker = new MiniMusicMakerApp();
			musicMaker.play(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
			}catch(Exception e){
				System.out.println("Something went wrong.");
			}
		}
}
}