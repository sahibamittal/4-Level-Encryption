import acm.graphics.*;



public class HCanvas extends GCanvas {
	
	public HCanvas(){
		GImage starter = new GImage("start.jpg");
		add(starter);
	}
	
	public void Encrypt(){
		GImage starter = new GImage("Encrypt.jpg");
		add(starter);
	}
	public void Eovr(){
		GImage starter = new GImage("Eovr.jpg");
		add(starter);
	}
	public void Etovr(int key){
		GImage starter = new GImage("Etovr.jpg");
		add(starter);
		GLabel lb =new GLabel(Integer.toString(key));
		add(lb,260,200);
	}
	public void Decrypt(){
		GImage starter = new GImage("Decrypt.jpg");
		add(starter);
	}
	public void Deovr(){
		GImage starter = new GImage("Deovr.jpg");
		add(starter);
	}
	
	
	
}
