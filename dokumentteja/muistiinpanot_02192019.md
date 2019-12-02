# MUISTIINPANOT 2.12.2019 – asiakastapaaminen / retrospekti / sprintin suunnittelu

## TASKIT

### YLEISTÄ

Retrospektiivi – kaikki, done

Sprintin suunnitelu kaikki, done

Herokun ylläpitäminen – Antti

Dokumentaatio – Aare

Sprintin backlogin alustus – Aare


### USER STORYT (ovat tässä prioriteettijärjestyksessä, *ks. tekstin lopusta tarvittaessa lisämuistiinpanoja*) + Tässä backlogiin menevät muotoilut

#### 1. Käyttäjänä näen sivustolle saapuessani suoraan listauksen lukuvinkeistä

hyväksymiskriteerien luominen: kaikki

redirectin lisäys: kaikki

yksikkötestin muuttamien: kaikki

cucumber testin muutamine: kaikki

picture toimionnallisuuden selvittäminen: Antti


#### 2. Käyttäjänä lisätessäni lukuvinkkiä minulla on mahdollisuus ensin hakea tiedot ISBN-tunnuksella ja nähdä ovatko ne oikeat, tai vaihtoehtoisesti syöttää lukuvinkin tiedot kokonaan itse

hyväksymiskriteerien luominen: kaikki

datan hakemisen suunnittelu: Santeri

datan hakemisen toteuttaminen: Santeri

uusi näkymä (kysytään isbn): Kaikki

isbn validointi: kaikki

muu tarvittava muutos  (esim sivuje redirecit): Kaikki

yksikkötestit: kaikki

cucumber testit: kaikki


#### 3. Käyttäjänä minulle näytetään lisäämäni nettilukuvinkin URL-osoite listauksessa

hyväksymiskriteerien luominen: kaikki

frontendin muutokset: Jesse 

cucumber-testit: kaikki


#### 4. Lisätessäni nettilukuvinkkiä haetaan automaattisesti tietoja sivusta, jolloin voin tarkistaa ja hyväksyä ne tai syöttää tiedot kokonaan itse

hyväksymiskriteerien luominen: kaikki

datan hakemisen suunnittelu: Jesse

datan hakemisen toteuttaminen: Jesse

uusi näkymä (kysytään onko tiedot ok): Kaikki

muu tarvittava muutos  (esim sivuje redirecit): Kaikki

yksikkötestit: kaikki

cucumber testit: kaikki


#### 5. Käyttäjänä voin lisätä lukuvinkiksi myös Youtube-videoita, jotka avautuvat niitä tarkasteltaessa uuteen ikkunaan

hyväksymiskriteerien luominen: kaikki

uusi lukuvinkkityyppi: kaikki

yksikkötestit: kaikki

cucumber testit: kaikki







## Asiakastapaamiseen liittyvää sälää (satunnaisia kirjattuja muistiinpanoja):

ohjaaja on itse paikalla maanantaina, suositteli sitä

User storyjen muistiinpanot
 
- uusi flow käyttöliitytmään: kaksi erillistä ruutua, polku, kysyy ensin tiedänkö kirjan ISBN tunnusta – jos tiedän, se hakee tiedot ja käyttäjä voi muokata niitä JOS EI TIEDÄ siirtää näkymään jossa voi itse antaa kaikki tiedot

- lukuvinkin url: eka lisää urlin, automaattisesti hakee esim. Titlen ja descriptionin ja kysyy onko ok ja voi lisätä ne suoraan - > muuten ilmeisesti manuaalinen

- voi lisätä videoita youtubesta (eri lukuvinkkityyppi)

- haluaa, että jos videolinkkejä, pystyy avamaan toiseksi sivuksi (tämä uuteen ikkunaan) ja kattomaan siinä

- URL näkyy -> 

- pikkumuokkaus: tervetuloa sovellukseen sijaan suoraan listaan lukuvinkeistä
