import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class KNAKnote extends JFrame implements ActionListener {

	JMenuBar menuBar;
	JMenu menuPlik, menuNarzêdzia, menuPomoc, menuKalkulatory, menuSzukaj, menuWygl¹d;
	JMenuItem mOtwórz, mZapisz, mWyjœcie, mNarz1, mMetryStopy, mCelToFar, mSzukaj, mKolor, mRozmiar, mOProgramie, mpKopiuj, mpWklej, mpDo³¹cz;
	JCheckBoxMenuItem chZawijanie;
	
	JTextArea notatnik;
	JPopupMenu popup;
	//JComboBox colorCombo;
	
	String tekstSchowek;

	public KNAKnote() {
		setTitle("KNAKnote");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		menuBar = new JMenuBar();
		menuPlik = new JMenu("Plik");
		mOtwórz = new JMenuItem("Otwórz", 'O');
		mZapisz = new JMenuItem("Zapisz");
		mWyjœcie = new JMenuItem("Wyjœcie");
		mWyjœcie.addActionListener(this);
		mWyjœcie.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));

		menuPlik.add(mOtwórz);
		mOtwórz.addActionListener(this);
		menuPlik.add(mZapisz);
		mZapisz.addActionListener(this);
		menuPlik.addSeparator();
		menuPlik.add(mWyjœcie);

		menuNarzêdzia = new JMenu("Narzêdzia");
		mNarz1 = new JMenuItem("Narzêdzie1");
		mNarz1.setEnabled(false);
		mMetryStopy = new JMenuItem("Metry => stopy");
		mMetryStopy.addActionListener(this);

		menuNarzêdzia.add(mNarz1);
		
		menuKalkulatory = new JMenu("Kalkulatory");
		mCelToFar = new JMenuItem("Celsjusz / Fahrenheit");
		menuKalkulatory.add(mMetryStopy);
		menuKalkulatory.add(mCelToFar);
		
		menuNarzêdzia.add(menuKalkulatory);
		
		menuSzukaj = new JMenu("ZnajdŸ");
		mSzukaj = new JMenuItem("ZnajdŸ");
		menuSzukaj.add(mSzukaj);
		mSzukaj.addActionListener(this);
		

		menuPomoc = new JMenu("Pomoc");
		mOProgramie = new JMenuItem("O programie");
		mOProgramie.addActionListener(this);
		menuPomoc.add(mOProgramie);
		
		menuWygl¹d = new JMenu ("Wygl¹d");
		mKolor = new JMenuItem("Kolor tekstu");
		menuWygl¹d.add(mKolor);
		mKolor.addActionListener(this);
		mRozmiar = new JMenuItem("Rozmiar tekstu");
		menuWygl¹d.add(mRozmiar);
		mRozmiar.addActionListener(this);
		chZawijanie = new JCheckBoxMenuItem("Zawijanie wierszy");
		chZawijanie.addActionListener(this);
		menuWygl¹d.add(chZawijanie);
		
		setJMenuBar(menuBar);
		menuBar.add(menuPlik);
	//	menuBar.add(menuNarzêdzia);
	//	menuBar.add(menuSzukaj);
		menuBar.add(menuWygl¹d);
		menuBar.add(menuPomoc);

		
		popup = new JPopupMenu();
		mpKopiuj = new JMenuItem("Kopiuj");
		mpKopiuj.addActionListener(this);
		mpWklej = new JMenuItem("Wklej");
		mpWklej.addActionListener(this);
		//mpDo³¹cz = new JMenuItem("Do³¹cz");
		//mpDo³¹cz.addActionListener(this);
		

		
		popup.add(mpKopiuj);
		popup.add(mpWklej);
		//popup.add(mpDo³¹cz);
		
		
		notatnik = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(notatnik, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		notatnik.setWrapStyleWord(true);
		scrollPane.setBounds(5,5,700 ,500 );
		
		add(scrollPane);
		notatnik.setLineWrap(false);
		notatnik.setComponentPopupMenu(popup); 
		



	}

	public static void main(String[] args) {
		KNAKnote appMenu = new KNAKnote();
		appMenu.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object Ÿród³o = e.getSource();
		if (Ÿród³o==mSzukaj) {
			String tekst = notatnik.getText();
			String znajdŸ = JOptionPane.showInputDialog("");
			String wyst¹pienia ="";
			int i = 0;
			int index;
			int startIndex = 0;
			while ((index = tekst.indexOf(znajdŸ, startIndex))!= -1) {
				startIndex = index + znajdŸ.length();
				i++;
				wyst¹pienia = wyst¹pienia + " " + index;
				
			}
			JOptionPane.showMessageDialog(null, znajdŸ + " wyst¹pi³o " +  i + " razy:" + wyst¹pienia);
			
			
			
		}
		else if (Ÿród³o==mOtwórz){
			JFileChooser fc = new JFileChooser();
			if (fc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
				File plik = fc.getSelectedFile();
				//JOptionPane.showMessageDialog(null, "Wybrany plik to: " + plik.getAbsolutePath());
				try {
					notatnik.setText("");
					setTitle("Kombajn (" + plik.getName() +")");
					Scanner skaner = new Scanner(plik);
					while(skaner.hasNext())
						notatnik.append(skaner.nextLine() + "\n");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		else if (Ÿród³o==mZapisz){
			JFileChooser fc = new JFileChooser();
			if (fc.showSaveDialog(null)==JFileChooser.APPROVE_OPTION) {
				File plik = fc.getSelectedFile();
				try {
					PrintWriter writer = new PrintWriter(plik);
					Scanner skaner = new Scanner(notatnik.getText());
					while (skaner.hasNext()){
						writer.println(skaner.nextLine() + "\n");
						writer.close();}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
		else if (Ÿród³o == mWyjœcie) {
			int odpowiedŸ = JOptionPane.showConfirmDialog(this, "Czy napewno chcesz wyjœæ?", "",
					JOptionPane.YES_NO_OPTION);
			if (odpowiedŸ == JOptionPane.YES_OPTION) {
				dispose();
			} else if (odpowiedŸ == JOptionPane.CLOSED_OPTION) {

			}

		}

		else if (Ÿród³o == chZawijanie) {
			if (chZawijanie.isSelected()) {
				notatnik.setLineWrap(true);
			} else if (!chZawijanie.isSelected()) {
				mNarz1.setEnabled(false);
			}

		}
		else if (Ÿród³o == mCelToFar) {
//			CelsiusToFarenheit.CelsiusToFarenheit();
//			CelsiusToFarenheit.CelsiusToFarenheit.setVisible(true);
		}
		if (Ÿród³o == mOProgramie) {
			JOptionPane.showMessageDialog(this, "Notatnik stworzony w ramach nauki SWING przez Bart³omiej Szczêœniak", "O programie",	JOptionPane.INFORMATION_MESSAGE);
		}
		if (Ÿród³o == mMetryStopy) {
			String sMetry = JOptionPane.showInputDialog("Podaj d³ugoœæ w metrach");
			double metry = Double.parseDouble(sMetry);
			double stopy = metry / 0.3048;
			String sStopy = String.format("%f.2", stopy);
			JOptionPane.showMessageDialog(this, metry + " metrów =" + sStopy + " stóp." + sStopy, "", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (Ÿród³o==mKolor){
			Color wybranyKolor = JColorChooser.showDialog(this, "Wybierz kolor czcionki", Color.BLACK);
			notatnik.setForeground(wybranyKolor);
		}
		else if (Ÿród³o==mRozmiar){
			String sRozmiarCzcionki = JOptionPane.showInputDialog(this, "Podaj rozmiar tekstu");
			int iRozmiarCzcionki = Integer.parseInt(sRozmiarCzcionki);
			notatnik.setFont(new Font ("SansSerif", Font.PLAIN, iRozmiarCzcionki));
		}
		else if (Ÿród³o==mpKopiuj){
			tekstSchowek = notatnik.getSelectedText();
			
		}
		else if (Ÿród³o==mpWklej){
			notatnik.insert(tekstSchowek, notatnik.getCaretPosition());
			
		}
		else if (Ÿród³o==mpDo³¹cz){
			
		}
	}

}
