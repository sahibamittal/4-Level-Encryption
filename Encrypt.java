import acm.program.*;
import acm.io.*;
import acm.util.*;
import java.awt.*;
import acm.graphics.*;
import acmx.export.javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class Encrypt extends ConsoleProgram {
	int tableRowSize = 26;
	int tableColumnSize = 26;
	int vignereTable[][] = new int[26][26];
	String rsaDecryted;
	int ref,valid;
	public static void main(String[] args) {
		new Encrypt().start(args);
	}

	public void init(){
		JButton visit=new JButton("Learn the Stuff");
		add(visit,NORTH);
		visit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			openURL("http://localhost/minor/icrypt101.html"); }
			} );
	
		can = new HCanvas();
		HCanvas sec=new HCanvas();
		add(can);		
		add(sec);
	}
	
	

public void run(){
	for (int rows = 0; rows < tableRowSize; rows++){
		for (int columns = 0; columns < tableColumnSize; columns++){
		vignereTable[rows][columns] = (rows + columns) % 26;
		}
		}
	String chr="yes";
	while( chr.equals("yes")||chr.equals("YES")){
		
		println("Welcome To iCrypt 101 !");
		println(" 1) Encrypt Your Text ?");
		println(" 2) Decrypt Your Text ?");
		println(" 3) Decrypt Your Image ?");
		println(" 4) Encrypt text using Vigenere");
		println(" 5) Decrypt text using Vigenere");
		println(" 6) Encrypt text using RSA : ");
		println(" 7) Decrypt text using RSA : ");
		
		println("Enter Your Choice");
	
	
		int choice = readInt();
	
		if(choice==1){
			can.Encrypt();
			println("Please use only a-z, 0-9 and spaces ");
			println("Special Character may lead to wrong message");
			println("Enter the String to Be Encrypted");
		
			SR=Initialise();
			println("what Do You want to Enccrypt to ");
			println(" 1) If you want to Encrypt to TXT");
			println(" 2) If you want to Encrypt to Image");
			int ch = readInt();
			if(ch==1){
				int Key=rgen.nextInt(5,20);
				ENC=EncryptA(SR,Key);
				Print(ENC);
				can.Etovr(Key);
			
			}
			if(ch==2){
				int Key=rgen.nextInt(5,20);
				println(""+Key);
				ENC=EncryptA(SR,Key);
				println("Enter the File Source :");
				String path ;
				path=readLine();
				BufferedImage ENIMG =null;
				ENIMG=EncryptImg(ENC,path,Key);
				write(ENIMG);
				can.Eovr();

			}
		
}
	
		if(choice==2){
			can.Decrypt();
			println("Enter your Key?:");
			int key=readInt();
			println("Entered the coded txt?:");
			SR=Initialise();
			ENC=EncryptA(SR,-key);
			Print(ENC);
			can.Deovr();
	
			}
	
		if(choice==3){
			can.Decrypt();
			BufferedImage EImage =InitIM();
			DecryptIMG(EImage);
			can.Deovr();
	
			}
		if(choice==4){
			println("Enter the plain text ::");
			String plainText=readLine(); 
			
			plainText = plainText.toUpperCase();
			println("Enter the key string : ");
			String key = readLine();
			key = key.toUpperCase();
			String cipherText = "";
			int keyIndex = 0;
			for (int ptextIndex = 0; ptextIndex < plainText.length();
			ptextIndex++){
			char pChar = plainText.charAt(ptextIndex);
			int asciiVal = (int) pChar;
			if (pChar == ' '){
			cipherText += pChar;
			continue;
			}
			if (asciiVal < 65 || asciiVal > 90){
			cipherText += pChar;
			continue;
			}
			int basicPlainTextValue = ((int) pChar) - 65;
			char kChar = key.charAt(keyIndex);
			int basicKeyValue = ((int) kChar ) - 65;
			int tableEntry = vignereTable[basicPlainTextValue][basicKeyValue];
			char cChar = (char) (tableEntry + 65);
			cipherText += cChar;
			keyIndex++;
			if (keyIndex == key.length())
			keyIndex = 0;
			}
			println(" Encrypted Message is :: "+cipherText);
		}
		if(choice==5){
			println("Enter the Decrypted text ");
			String cipherText = readLine();
			cipherText = cipherText.toUpperCase();
			print("Enter the key string : ");
			String key = readLine();
			key = key.toUpperCase();
			String plainText = "";
			int keyIndex = 0;
			for (int ctextIndex = 0; ctextIndex < cipherText.length();
			ctextIndex++){
			char cChar = cipherText.charAt(ctextIndex);
			int asciiVal = (int) cChar;
			if (cChar == ' '){
			plainText += cChar;
			continue;
			}
			if (asciiVal < 65 || asciiVal > 90){
			plainText += cChar;
			continue;
			}
			int basiccipherTextValue = ((int) cChar) - 65;
			char kChar = key.charAt(keyIndex);
			int basicKeyValue = ((int) kChar ) - 65;
			for (int pIndex = 0; pIndex < tableColumnSize; pIndex++){
			if (vignereTable[basicKeyValue][pIndex] == basiccipherTextValue){
			char potcChar = (char)
			(vignereTable[basicKeyValue][pIndex] + 65);
			char potpChar = (char) (pIndex + 65);
			plainText += potpChar;
			}
			}
			keyIndex++;
			if (keyIndex == key.length())
			keyIndex = 0;
			}
		println(" Decrypted Message is:: "+plainText);
		}
		if(choice==6)
		{
		rsaEncryption();
		}
		if(choice==7)
		{
println("Enter your refrence number ::");
ref=readInt();
if(ref==valid)
	println("The Decrypted text :: "+rsaDecryted);
else println("wrong refrence number");
		}
		println("Do you wish to run the program again(yes/no)");
		chr=readLine();
		println(chr);
	
	} 

}



BigInteger p;  
BigInteger q;  
BigInteger N;  
BigInteger phi;  
BigInteger e;  
BigInteger d;  
int bitlength = 1024;  
int blocksize = 256;
Random r = new Random(); 
//rsa encryption starts here...
private void rsaEncryption() {
	println("Enter the plain text : ");
	String teststring=readLine();
	     
        
        p = BigInteger.probablePrime(bitlength, r); 
      
        q = BigInteger.probablePrime(bitlength, r);  
          N = p.multiply(q);  
            
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));  
          e = BigInteger.probablePrime(bitlength/2, r);  
          
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0 ) {  
            e.add(BigInteger.ONE);  
        }  
 d = e.modInverse(phi);   
 println("Encrypting String: " + teststring);  
 //println("teststring.getbytes"+teststring.getBytes());
 //println("String in Bytes: " + bytesToString(teststring.getBytes()));
 byte[] encrypted = encrypt(teststring.getBytes());                    
 //println("Encrypted String in Bytes: " + bytesToString(encrypted));  
   println("Your String has been Encrypted ::");
   ref=r.nextInt(100);
   valid=ref;
   println("your refrence number :: "+ ref);
 byte[] decrypted = decrypt(encrypted);        
 //println("Decrypted String in Bytes: " +  bytesToString(decrypted));  
 rsaDecryted=new String(decrypted);
 
}
private static String bytesToString(byte[] encrypted) {  
    String test = "";  
    for (byte b : encrypted) {  
        test += Byte.toString(b);  
    }  
    return test;  
}  
public byte[] encrypt(byte[] message) {       
    return (new BigInteger(message)).modPow(e, N).toByteArray();  
}
public byte[] decrypt(byte[] message) {  
    return (new BigInteger(message)).modPow(d, N).toByteArray();  
} 
//decrypt image starts  here...

public void DecryptIMG (BufferedImage DEM){
	
	ArrayList<String> Detxt= new ArrayList<String>();
	
	int pixelarray[][] = new int[DEM.getHeight()][DEM.getWidth()];
	int height= DEM.getHeight();
	int width= DEM.getWidth();
	for(int i =0 ; i<height ; i++){
		for(int j=0; j<width ; j++)
			pixelarray[i][j]=DEM.getRGB(j, i);
	}
	
	int pix,key=0;
	pix=pixelarray[height-1][width-1];
	if(GImage.getRed(pix)==GImage.getBlue(pix))
	key=GImage.getRed(pix);
	pix=pixelarray[height-1][width-2];
	int size=GImage.getRed(pix);
	int[] strsize = new int[size];
	for(int i=0;i<size;i++){
		int pixe=pixelarray[height-i-2][width-1];
		strsize[i]=GImage.getRed(pixe);
	}
	
	
	for(int i =0 ; i<height ; i++){
		String str = "";
		for(int j=0; j<width ; j++){
			int pixel  = pixelarray[i][j]; 
			int g = GImage.getGreen(pixel);
			if (i<size&&j<strsize[i]){
			char ch;
			if(g<27){
			ch=(char)('A'+ g );
			str+=ch;
			}
			if(g==27){
				ch=' ';
				str+=ch;
				}
			if(g>27){
				int num=0+g-27;
				str+=Integer.toString(num);
				}
			}
		}
		if (i<size)
		Detxt.add(i, str);
	}
	ArrayList<String> DE = EncryptA(Detxt,-key);
	Print(DE);
	
}

private BufferedImage EncryptImg(ArrayList<String> EN,String path,int key){
	
	int cnt=0;
	BufferedImage src = null;
	try {
		src = ImageIO.read(new File(path));
	} catch (IOException e) {
		e.printStackTrace();
	}
	int height=src.getHeight();
	int width=src.getWidth();
	
	
	int[][] pixelarray=new int[height][width];
	for (int i=0; i<EN.size(); i++){
		cnt+=EN.get(i).length();
	}
	
	for(int i =0 ; i<height ; i++){
		for(int j=0; j<width ; j++)
	pixelarray[i][j] = src.getRGB(j, i);
	}
	
	for(int i =0 ; i<height ; i++){
		for(int j=0; j<width ; j++){
			int pixel  = pixelarray[i][j]; 
			
			int r = GImage.getRed(pixel);
			int b = GImage.getBlue(pixel);
			int g = GImage.getGreen(pixel);
			
			if(cnt>0&&i<EN.size()&&j<EN.get(i).length()){
				char temp;
				temp=EN.get(i).charAt(j);
				if(Character.isLetter(temp)){
					g=temp-'A';
					}
				if(Character.isDigit(temp)){
					g=temp-'0'+27;
					}
				if(Character.isSpaceChar(temp)){
					g=27;
					
				}
				pixelarray[i][j]=GImage.createRGBPixel(r,g,b);			
			}
			
		}
		
	}
	pixelarray[height-1][width-1]=GImage.createRGBPixel(key, key, key);
	pixelarray[height-1][width-2]=GImage.createRGBPixel(EN.size(), EN.size(), EN.size());
	
	for(int i=2; i<EN.size()+2;i++){
		pixelarray[height-i][width-1]=GImage.createRGBPixel(EN.get(i-2).length(),EN.get(i-2).length(),EN.get(i-2).length());
	}
	
	BufferedImage I = null;
	try {
		I = ImageIO.read(new File(path));
	} catch (IOException e) {
		e.printStackTrace();
	}
	for(int i =0 ; i<height ; i++){
		for(int j=0; j<width ; j++)
			I.setRGB(j,i, pixelarray[i][j]);
	}
	
	return I;
	
}


public BufferedImage InitIM(){
	println("Enter the Source");
	String path=readLine();
	BufferedImage src = null;
	try {
		src = ImageIO.read(new File(path));
	} catch (IOException b) {
		b.printStackTrace();
	}
	return src;
}
	
public void write(BufferedImage ENIMG){
	String ext="bmp";
	String fileName = "Encrypted";
	    File file = new File(fileName + "." + ext);
	    try {
	        ImageIO.write((RenderedImage)ENIMG, ext, file);  // ignore returned boolean
	    } catch(IOException e) {
	        System.out.println("Write error for " + file.getPath() +
	                           ": " + e.getMessage());
	    }
}


private ArrayList<String> Initialise(){
	
	ArrayList<String> EN = new ArrayList<String>();
	String str;
	int cnt=0;
	while(true){
		println("Enter The Message ");
		str=readLine();
		if(str.equals("")) 
			break;
		EN.add(cnt, str);
		cnt++;
	}
	return EN;
}

private ArrayList<String> EncryptA(ArrayList<String>  EN,int Key ){
	for(int i=0; i<EN.size();i++){
		String Str="";
		for(int j=0; j<EN.get(i).length() ;j++ ){
			char ch,temp;
			temp=EN.get(i).charAt(j);
			if(Character.isLetter(temp))
			ch=(char)('A'+( Character.toUpperCase(temp)-'A'+Key+26)%26);
			else if(Character.isDigit(temp)){
			ch=(char)('0'+ 0+( temp-'0'+Key+20)%10);
			
			}
			else
				ch=temp;
			Str+=ch;
			
		}
		EN.set(i, Str);
	}
	return EN;
}

private void Print(ArrayList<String> EN){
	
	for(int i=0; i<EN.size();i++){
		String Str=EN.get(i);
		println("Encrypted : "+Str);
	
	}
	
}
public static void openURL(String url) {
	String osName = System.getProperty("os.name");
	try {
	if (osName.startsWith("Windows"))
	Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
	else { 
	String[] browsers = {"firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape" };
	String browser = null;
	for (int count = 0; count < browsers.length && browser == null; count++)
	if (Runtime.getRuntime().exec(new String[] {"which", browsers[count]}).waitFor() == 0)
	browser = browsers[count];
	Runtime.getRuntime().exec(new String[] {browser, url});
	}
	}
	catch (Exception e) {
	JOptionPane.showMessageDialog(null, "Error in opening browser" + ":\n" + e.getLocalizedMessage());
	}
	}
HCanvas can;
ArrayList<String> ENC = new ArrayList<String>();
private ArrayList<String> SR = new ArrayList<String>();
private RandomGenerator rgen = new RandomGenerator();


}