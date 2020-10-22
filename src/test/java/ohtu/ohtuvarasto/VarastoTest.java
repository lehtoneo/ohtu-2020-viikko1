package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto varasto2;
    Varasto kayttoKelvoton;
    Varasto varastoAlkuSaldolla;
    Varasto varastoAlkuSaldolla2;
    Varasto varastoAlkuSaldolla3;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        varasto2 = new Varasto(10);
        kayttoKelvoton = new Varasto(-1);
        varastoAlkuSaldolla = new Varasto(10, 1);
        varastoAlkuSaldolla2 = new Varasto(10, -1);
        varastoAlkuSaldolla3 = new Varasto(-1, -1);
    }
    @Test
    public void josAlkuSaldoOnEnemmanKuinTilavuusSaldoOnTilavuus() {
        Varasto v = new Varasto(10, 11);
        assertEquals(10, v.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void alkuSaldollaTilavuusOikein() {
        
        assertEquals(10, varastoAlkuSaldolla.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void alkuSaldollaSaldoOikein() {
        assertEquals(1, varastoAlkuSaldolla.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenAlkuSaldoOn0() {
        assertEquals(0, varastoAlkuSaldolla2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenTilavuusOn0AlkuSaldolla() {
        assertEquals(0, varastoAlkuSaldolla3.getTilavuus(), vertailuTarkkuus);
    }
    
    
    @Test
    public void josTilavuuttaEiOleTuleeKayttokelvoton() {
        assertEquals(0.0, kayttoKelvoton.getTilavuus(), vertailuTarkkuus);
    }

   
    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void josLisataanMiinustaEiTapahduMitaan() {
        double aluksi = varasto2.getSaldo();
        varasto2.lisaaVarastoon(-1);
        assertEquals(aluksi, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void josLisataanEnemmanKuinTilaaOnVarastoTaynna() {
        varasto2.lisaaVarastoon(20);
        assertEquals(10, varasto2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void josOtetaanMiinustaEiTehdäMitään() {
        varasto2.otaVarastosta(-1);
        assertEquals(10, varasto2.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    
    
    @Test
    public void josOtetaanEnemmänKuinSaldoNiinSaldoksiTuleeNolla() {
        varasto2.otaVarastosta(11);
        assertEquals(0, varasto2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    
    @Test
    public void toStringToimiiOikein() {
        Varasto v = new Varasto(10, 1);
        String haluttu = "saldo = " + 1.0 + ", vielä tilaa " + 9.0;
        System.out.println(haluttu);
        System.out.println(v.toString());
        assertEquals(haluttu, v.toString());
    }

}