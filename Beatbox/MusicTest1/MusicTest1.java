import javax.sound.midi.*;

class MusicTest1{
	public void play(){
		try{
			Sequencer sequencer = MidiSystem.getSequencer();
			System.out.println("We got a sequencer.");
		}catch(MidiUnavailableException e){
			System.out.println("Oops no sequencer.");
			e.printStackTrace();
		}catch(Exception ex){
			System.out.println("Got a different exception than MidiUnavailable");
			ex.printStackTrace();
		}
		
	}	

	public static void main(String[] args){
		MusicTest1 mt = new MusicTest1();
		mt.play();
	}
}