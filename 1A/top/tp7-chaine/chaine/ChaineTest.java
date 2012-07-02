package chaine;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ChaineTest {
	
	private Chaine chaine;
	private Chaine vide;
	
	@Before
	public void before() {
		chaine = new Chaine("chaine");
		vide = new Chaine();
	}
	
	@Test
	public void testEstVide() {
		chaine = new Chaine();
		assertTrue(chaine.estVide());
		chaine.tete = new Element('?');
		assertFalse(chaine.estVide());
	}
	
	@Test
	public void testReste() {
		Element a = new Element('a');
		Element b = new Element('b');
		
		a.suite = b;
		
		chaine = new Chaine();
		chaine.tete = a;
		
		assertEquals("b", chaine.reste().toString());
	}
	
	@Test(expected = ChaineVideException.class)
	public void testPremier() throws ChaineVideException {
		assertEquals('c', chaine.premier());
		
		assertEquals('?', new Chaine().premier());
	}
	
	@Test
	public void testAdjT() {
		chaine = new Chaine("Pad");
		chaine.adjT('i');
		assertEquals("iPad", chaine.toString());
	}
	
	@Test
	public void testLongueur() {
		assertEquals(6, chaine.longueur());
		assertEquals(0, vide.longueur());
	}
	
	@Test
	public void testEstMembre() throws ChaineVideException {
		assertTrue(chaine.estMembre('c'));
		assertTrue(chaine.estMembre('i'));
		assertTrue(chaine.estMembre('e'));
		assertFalse(chaine.estMembre('o'));
	}
	
	@Test
	public void testOccurence() throws ChaineVideException {
		assertEquals(0, vide.occurence('c'));
		
		chaine = new Chaine("occurence");
		assertEquals(3, chaine.occurence('c'));
		assertEquals(1, chaine.occurence('o'));
		assertEquals(2, chaine.occurence('e'));
		assertEquals(0, chaine.occurence('a'));
	}
	
	@Test
	public void testSupprime() throws ChaineVideException {
		chaine = new Chaine("occurence");
		assertEquals("occurence", chaine.supprime('a').toString());
		assertEquals("ccurence", chaine.supprime('o').toString());
		assertEquals("occurnce", chaine.supprime('e').toString());
	}
	
	@Test
	public void testSupprimeTout() throws ChaineVideException {
		chaine = new Chaine("occurence");
		assertEquals("occurence", chaine.supprimeTout('a').toString());
		assertEquals("ccurence", chaine.supprimeTout('o').toString());
		assertEquals("occurnc", chaine.supprimeTout('e').toString());
	}
	
	@Test
	public void testTousDifferents() throws ChaineVideException {
		assertTrue(chaine.tousDifferents());
		
		assertTrue(vide.tousDifferents());
		
		chaine = new Chaine("occurence");
		assertFalse(chaine.tousDifferents());
	}
	
	@Test(expected = ChaineVideException.class)
	public void testDernier() throws ChaineVideException {
		assertEquals('e', chaine.dernier());
		
		chaine = new Chaine("c");
		assertEquals('c', chaine.dernier());
		
		assertEquals('?', vide.dernier());
		//TODO(Since last assertion throws an exception, nothing can be done after)
	}
	
	@Test(expected = ChaineVideException.class)
	public void testDeuxieme() throws ChaineVideException {
		assertEquals('h', chaine.deuxieme());
		
		chaine = new Chaine("hi");
		assertEquals('i', chaine.deuxieme());
		
		assertEquals('?', vide.deuxieme());
	}
	
	@Test
	public void testSaufDernier() throws ChaineVideException {
		assertEquals("chain", chaine.saufDernier().toString());
		
		chaine = new Chaine("c");
		assertEquals("", chaine.saufDernier().toString());
	}
	
	@Test
	public void testNieme() throws ChaineVideException {
		assertEquals('c', chaine.nieme(1));
		assertEquals('a', chaine.nieme(3));
		assertEquals('e', chaine.nieme(chaine.longueur()));
	}
	
	@Test
	public void testNPremiers() throws ChaineVideException {
		assertEquals("", chaine.nPremiers(0).toString());
		assertEquals("c", chaine.nPremiers(1).toString());
		assertEquals("cha", chaine.nPremiers(3).toString());
		assertEquals("chaine", chaine.nPremiers(chaine.longueur()).toString());
	}
	
	@Test
	public void testNDerniers() {
		assertEquals("", chaine.nDerniers(0).toString());
		assertEquals("e", chaine.nDerniers(1).toString());
		assertEquals("ine", chaine.nDerniers(3).toString());
		assertEquals("chaine", chaine.nDerniers(chaine.longueur()).toString());
	}
	
	@Test
	public void testRetourne() throws ChaineVideException {
		assertEquals("eniahc", chaine.retourne().toString());
		
		chaine = new Chaine("été");
		assertEquals("été", chaine.retourne().toString());
		
		chaine = new Chaine("occurence");
		assertEquals("ecnerucco", chaine.retourne().toString());
		
		chaine = new Chaine("vu");
		assertEquals("uv", chaine.retourne().toString());
		
		chaine = new Chaine("c");
		assertEquals("c", chaine.retourne().toString());
		
		assertEquals("", vide.retourne().toString());
	}
	
	@Test
	public void testConcat() throws ChaineVideException {
		assertEquals("chaine", chaine.concat(new Chaine()).toString());
		assertEquals("chainement", chaine.concat(new Chaine("ment")).toString());
		
		assertEquals("suffix", vide.concat(new Chaine("suffix")).toString());
	}
	
	@Test(expected = ChaineVideException.class)
	public void testMinCh() throws ChaineVideException {
		assertEquals('a', chaine.minCh());
		
		chaine = new Chaine("zOo");
		assertEquals('O', chaine.minCh());
		
		chaine = new Chaine("chAine");
		assertEquals('A', chaine.minCh());
		
		assertEquals('?', vide.minCh());
	}
	
	@Test
	public void testCroissante() throws ChaineVideException {
		assertEquals(false, chaine.croissante());
		
		assertEquals(true, vide.croissante());
		
		chaine = new Chaine("c");
		assertEquals(true, chaine.croissante());
		
		chaine = new Chaine("abcd");
		assertEquals(true, chaine.croissante());
		
		chaine = new Chaine("ABCD");
		assertEquals(true, chaine.croissante());
		
		chaine = new Chaine("aBcD");
		assertEquals(false, chaine.croissante());
	}
	
	@Test
	public void testNNaturels() throws ChaineVideException {
		assertEquals("", chaine.nNaturels(0).toString());
		assertEquals("1", chaine.nNaturels(1).toString());
		assertEquals("12345", chaine.nNaturels(5).toString());
	}
	
	@Test
	public void testPalindrome() throws ChaineVideException {
		assertEquals(false, chaine.palindrome());
		
		assertEquals(true, vide.palindrome());
		
		chaine = new Chaine("c");
		assertEquals(true, chaine.palindrome());
		
		chaine = new Chaine("été");
		assertEquals(true, chaine.palindrome());
	}
	
	@Test
	public void testAnagramme() throws ChaineVideException {
		assertEquals(false, chaine.anagramme(new Chaine()));
		assertEquals(false, chaine.anagramme(new Chaine("c")));
		assertEquals(true, chaine.anagramme(new Chaine("aniche")));
				
		assertEquals(true, vide.anagramme(new Chaine()));
		assertEquals(false, vide.anagramme(new Chaine("c")));
		assertEquals(false, vide.anagramme(new Chaine("caniche")));
	}
	
	@Test
	public void testUnion() throws ChaineVideException {
		assertEquals("chaine", chaine.union(new Chaine()).toString());
		assertEquals("chaine", chaine.union(new Chaine("c")).toString());
		assertEquals("chaine", chaine.union(new Chaine("chaine")).toString());
		assertEquals("chaine", chaine.union(new Chaine("eniahc")).toString());
		assertEquals("chainet", chaine.union(new Chaine("net")).toString());
		assertEquals("chaineou", chaine.union(new Chaine("ou")).toString());
		
		assertEquals("", vide.union(new Chaine("")).toString());
		assertEquals("chaine", vide.union(new Chaine("chaine")).toString());
	}
	
	@Test
	public void testDifference() throws ChaineVideException {
		assertEquals("chaine", chaine.difference(new Chaine()).toString());
		assertEquals("", chaine.difference(new Chaine("chaine")).toString());
		assertEquals("hai", chaine.difference(new Chaine("occurence")).toString());
		
		assertEquals("", vide.difference(new Chaine()).toString());
		assertEquals("", vide.difference(new Chaine("chaine")).toString());
	}
}

