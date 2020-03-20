package desarrollo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SopaDeLetras {
	private int cantPalabras;
	private int dimension;
	private char[][] sopa;
	private List<String> palabras = new ArrayList<String>();

	public void inicializar(String arch) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(arch));
		String linea[] = br.readLine().split(" ");
		String letras;
		this.dimension = Integer.parseInt(linea[0]);
		this.cantPalabras = Integer.parseInt(linea[1]);
		this.sopa = new char[8][8];
		for (int i = 0; i < this.dimension; i++) {
			letras = br.readLine();
			for (int j = 0; j < this.dimension; j++) {
				this.sopa[i][j] = letras.charAt(j);
			}
		}
		for (int i = 0; i < this.cantPalabras; i++) {
			this.palabras.add(br.readLine());
		}

		br.close();
	}

	public void mostrar() {
		for (int i = 0; i < this.dimension; i++) {
			for (int j = 0; j < this.dimension; j++) {
				System.out.print(sopa[i][j]);
			}
			System.out.println();
		}
	}

	public void getPalabras() {
		Iterator<String> ite = this.palabras.iterator();
		while(ite.hasNext()) {
			System.out.println(ite.next());
		}
	}
	
	public void buscarPalabras(String salida) throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter(salida));
		int flag;
		for(int k = 0 ; k < this.cantPalabras ; k++) {
			flag = 0;
			for(int i = 0; i < this.dimension ; i++) {
				for (int j = 0; j < this.dimension ; j++) {
					if (flag == 1) {
						break;
					}
					if(this.palabras.get(k).charAt(0) == this.sopa[i][j]) {
						if(buscarNorte(this.palabras.get(k),i,j)) {
							pw.println((k+1) + " N");
							flag = 1;
						}
						else if(buscarSur(this.palabras.get(k),i,j)) {
							pw.println((k+1) + " S");
							flag = 1;
						}
						else if(buscarEste(this.palabras.get(k),i,j)) {
							pw.println((k+1) + " E");
							flag = 1;
						}
						else if(buscarOeste(this.palabras.get(k),i,j)) {
							pw.println((k+1) + " O");
							flag = 1;
						}
					}
				}
			}
		}
		pw.close();
	}

	private boolean buscarNorte(String palabra,int i,int j) {
		for(int k = 0;k < palabra.length() ; k++) {
			if(i < 0) {
				return false;
			}
			if(palabra.charAt(k) != this.sopa[i][j]) {
				return false;
			}
			i--;
		}
		return true;
	}
	
	private boolean buscarSur(String palabra,int i,int j) {
		for(int k = 0;k < palabra.length() ; k++) {
			if(i >= this.dimension) {
				return false;
			}
			if(palabra.charAt(k) != this.sopa[i][j]) {
				return false;
			}
			i++;
		}
		return true;
	}
	
	private boolean buscarEste(String palabra,int i,int j) {
		for(int k = 0;k < palabra.length() ; k++) {
			if(j >= this.dimension) {
				return false;
			}
			if(palabra.charAt(k) != this.sopa[i][j]) {
				return false;
			}
			j++;
		}
		return true;
	}
	
	private boolean buscarOeste(String palabra,int i,int j) {
		for(int k = 0;k < palabra.length() ; k++) {
			if(j < 0) {
				return false;
			}
			if(palabra.charAt(k) != this.sopa[i][j]) {
				return false;
			}
			j--;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		SopaDeLetras s1 = new SopaDeLetras();
		s1.inicializar("rapigrama.in");
		s1.mostrar();
		s1.getPalabras();
		s1.buscarPalabras("rapigrama.out");
	}

}
