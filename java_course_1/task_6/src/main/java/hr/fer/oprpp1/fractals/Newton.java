package hr.fer.oprpp1.fractals;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import hr.fer.oprpp1.math.Complex;
import hr.fer.oprpp1.math.ComplexPolynomial;
import hr.fer.oprpp1.math.ComplexRootedPolynomial;
import hr.fer.zemris.java.fractals.viewer.FractalViewer;
import hr.fer.zemris.java.fractals.viewer.IFractalProducer;
import hr.fer.zemris.java.fractals.viewer.IFractalResultObserver;


public class Newton {

	public static void main(String[] args) {
		System.out.println("Welcome to Newton-Raphson iteration-based fractal viewer.");
		System.out.println("Please enter at least two roots, one root per line. Enter 'done' when done.");
		int rootCount = 1;
		Scanner scanner = new Scanner(System.in);
		List<Complex> roots = new ArrayList<>();
		while(true) {
			System.out.print("Root " + rootCount + "> ");
			if(scanner.hasNextLine()) {
				String input = scanner.nextLine().trim();
				if(input.equals("done")) {
					if(rootCount < 3) {
						System.out.println("Please enter at least two roots, one root per line.");
						continue;
					} else {
						System.out.println("Image of fractal will appear shortly. Thank you.");
						break;
					}
				} 
				try {
					Complex root = takeComplex(input);
					roots.add(root);
				} catch(IllegalArgumentException ex) {
					System.out.println(ex.getMessage());
					continue;
				}
				rootCount++;
			} else {
				System.out.println("Scanner does not have next token!");
			}
		}
		scanner.close();
		Complex[] rootsArray = new Complex[roots.size()];
		roots.toArray(rootsArray);
		FractalViewer.show(new MojProducer(rootsArray));	
	}
	
	/**	Method checks wether the user's input is in form of an appropriate complex number.
	 * 	If it is, method creates an instance of Complex and returns that instance. 	
	 * 
	 * @param String input that user has entered
	 * @return Complex number 
	 * @throws IllegalArgumentException if form of complex number is not valid
	 */
	private static Complex takeComplex(String input) {
		char[] data = input.toCharArray();
		int currentIndex = 0;
		StringBuilder rCollector = new StringBuilder();
		StringBuilder iCollector = new StringBuilder();
		double real = 0;
		double imag = 0;
		boolean isMinus = false;
		if(Character.compare(data[currentIndex], '-') == 0) {
			isMinus = true; 
			currentIndex++;
		}
		while(currentIndex < data.length) {
			if(Character.isDigit(data[currentIndex])) {
				rCollector.append(data[currentIndex++]);
				continue;
			}
			break;
		}
		boolean mustHaveOperator = false;
		if(!rCollector.isEmpty()) {
			real = Double.parseDouble(rCollector.toString());
			mustHaveOperator = true;
		}
		if(isMinus) {
			isMinus = false;
			real = -real;
		}
		currentIndex = skipBlanks(data, currentIndex);
		if(currentIndex < data.length) {
			if(Character.compare(data[currentIndex], '-') == 0 && !isMinus) {
				isMinus = true;
				currentIndex++;
			} else if(Character.compare(data[currentIndex], '+') == 0 ) {
				currentIndex++;
			} else if(mustHaveOperator) {
				invalidInput();
			}
		}
		currentIndex = skipBlanks(data, currentIndex);
		if(currentIndex < data.length) {
			if (Character.compare(data[currentIndex], 'i') == 0) {
				currentIndex++;
				imag = 1;
			} else 
				invalidInput();
		}
		currentIndex = skipBlanks(data, currentIndex);
		while(currentIndex < data.length) {
			if(Character.isDigit(data[currentIndex])) {
				iCollector.append(data[currentIndex++]);
				continue;
			} else 
				invalidInput();
		}
		if(!iCollector.isEmpty() ) 
			imag = Double.parseDouble(iCollector.toString());
		if(isMinus)
			imag = -imag;
		return new Complex(real, imag);
	}
	
	/** Private method removes all the empty characters such as space, tabulator
	 * 	and tranisitions to a new row.
	 *
	 */
	private static int skipBlanks(char[] data, int currentIndex) {
		while(currentIndex < data.length) {
			char c = data[currentIndex];
			if(c == ' ' || c == '\t' || c == '\r' || c == '\n') {
				currentIndex++;
				continue;
			}
			break;
		}
		return currentIndex;
	} 
	
	/**	Throws error with appropriate message when user's input has not been accepted. 
	 * 	Only purpose of this method is structuring more clean code.
	 * 
	 */
	private static void invalidInput() {
		throw new IllegalArgumentException("Only supported inputs are complex numbers and command 'done'.\n"
				+ "Syntax for complex numbers is of form a+ib or a-ib where parts that are zero can be dropped, but not both.");
	}
	
	public static class MojProducer implements IFractalProducer {
		private Complex[] roots;
		
		public MojProducer(Complex[] roots) {
			super();
			this.roots = roots;
		}

		@Override
		public void produce(double reMin, double reMax, double imMin, double imMax,
				int width, int height, long requestNo, IFractalResultObserver observer, AtomicBoolean cancel) {
			int m = 16*16*16;
			int offset = 0;
			
			ComplexRootedPolynomial rootedPoly = new ComplexRootedPolynomial(
					new Complex(1, 0), this.roots
					);
//			ComplexRootedPolynomial rootedPoly = new ComplexRootedPolynomial(
//					new Complex(1, 0), new Complex(1, 0), new Complex(-1, 0), new Complex(0, 1), new Complex(0, -1)
//					);
			ComplexPolynomial poly = rootedPoly.toComplexPolynom();
			
			short[] data = new short[width * height];
			double cre;
			double cim;
			Complex c;
			Complex zn;
			Complex znold;
			int iter;
			Complex numerator;
			Complex denominator;
			Complex fraction;
			double module = 0;
			for(int y = 0; y < height; y++) {
				if(cancel.get()) break;
				for(int x = 0; x < width; x++) {
					cre = x / (width-1.0) * (reMax - reMin) + reMin;
					cim = (height-1.0-y) / (height-1) * (imMax - imMin) + imMin;
					c = new Complex(cre, cim);
					zn = c;
					iter = 0;
					do {
						numerator = poly.apply(zn);
						denominator = poly.derive().apply(zn);;
						znold = zn;
						fraction = numerator.divide(denominator);
						zn = zn.sub(fraction);
						module = zn.sub(znold).module();
						iter++;
					} while(iter < m && module > 0.001);
					data[offset++] = (short) (rootedPoly.indexOfClosestRootFor(zn, 0.002) + 1);
				}
			}
			observer.acceptResult(data, (short)(poly.order() + 1), requestNo);
		}
	}
}
