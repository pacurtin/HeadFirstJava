import javax.sound.midi.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;

class RandomMusicVideo{

	static JFrame f = new JFrame("Music Video");
	static MyDrawPanel m1;

	public static void main(String[] args){
			RandomMusicVideo video = new RandomMusicVideo();
			video.play();
	}

	public void setUpGui(){
		m1 = new MyDrawPanel();
		f.setContentPane(m1);
		f.setBounds(30,30,300,300);
		f.setVisible(true);
	}

	public void play(){
		setUpGui();
		
		try{
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.open();
			int[] eventsIWant={127};
			sequencer.addControllerEventListener(m1, eventsIWant);
			Sequence seq =new Sequence(Sequence.PPQ, 4);
			Track track = seq.createTrack();

			for(int i=5;i<61;i+=4){
				track.add(makeEvent(144,1,i,100,i));	//144=SHORTMESSAGE.NOTE_ON
				track.add(makeEvent(176,1,127,0,i));	//176=SHORTMESSAGE.CONTROLLER_EVENT
				//By listening for this event we can trigger things in time to the music.
				track.add(makeEvent(128,1,i,100,i+2));	//128=SHORTMESSAGE.NOTE_OFF
			}
			
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

	class MyDrawPanel extends JPanel implements ControllerEventListener {
		boolean msg = false; //set to true when event recieved
		
		public void controlChange(ShortMessage event){
			msg = true;
			repaint();
		}
		
		public void paintComponent(Graphics g){
			if (msg){
				Graphics2D g2 = (Graphics2D) g;
				int r = (int) (Math.random()*250);
				int gr = (int) (Math.random()*250);
				int b = (int) (Math.random()*250);

				g.setColor(new Color(r,gr,b));

				int ht = (int) (Math.random()*120) + 10;
				int width = (int) (Math.random()*120) + 10;
				int x = (int) (Math.random()*160) + 10;
				int y = (int) (Math.random()*160) + 10;
				g.fillRect(x,y,ht,width);
				msg = false;
			}
		}
		
		
	}

}