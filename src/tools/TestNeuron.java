package tools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import struct.Network;

public class TestNeuron {

	public static void main(String[] args) {
		/*Network xorNet = new Network("mininet.txt");
		System.out.print(xorNet.output.toString() + "\n\n");
		
		double[][] testMatrix = new double[][]{
                new double[] { 0.0, 0.0 },
                new double[] { 0.0, 1.0 },
                new double[] { 1.0, 0.0 },
                new double[] { 1.0, 1.0 }
                };
		for(int i = 0; i < testMatrix.length; i++){
			xorNet.computeNetwork(testMatrix[i]);
			System.out.print("Input [" + testMatrix[i][0] + "," + testMatrix[i][1] + "] Output " + xorNet.output.toString() + "\n");
		}*/
		
		Network newNet = new Network("new.net");
		//System.out.print(newNet.output.toString() + "\n\n");
		
		double[][] testMatrix = new double[1000][256];
		
		/* Leemos imagen y rellenamos matriz */
		
		try {
			
			BufferedImage digits = ImageIO.read(new File("digits.png"));
			int index = 0;
			for(int y = 0; y < digits.getHeight(); y+=16){
				for(int x = 0; x < digits.getWidth() ; x+=16){
									
					for(int y2 = 0; y2 < 16; y2++){
						for(int x2 = 0; x2 < 16; x2++){
							
							int RGB = digits.getRGB(x2+x, y2+y);
							int greyScale = (digits.getColorModel().getRed(RGB) + digits.getColorModel().getGreen(RGB) + digits.getColorModel().getBlue(RGB))/3;
							
							
							//testMatrix[index][y2*16+x2] = greyScale/255; // Black 0 - White 1
							testMatrix[index][y2*16+x2] = Math.abs(greyScale/255 - 1); // Black 1 - White 0
						}
					}
					index++;
				}
			}	

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		/* Comprobamos con la red*/
		
		for(int i = 0; i < testMatrix.length; i++){
			newNet.computeNetwork(testMatrix[i]);
			System.out.print("Input [" + i + "] Output " + newNet.output.toString() + "\n");
		}
		
	}

}
