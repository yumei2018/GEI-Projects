package gov.ca.water.shapelite.projection.categories.projected;
import gov.ca.water.shapelite.projection.ProjectionInfo;
import gov.ca.water.shapelite.projection.categories.CoordinateSystemCategory;

public class UtmOther extends CoordinateSystemCategory {
    //<editor-fold defaultstate="collapsed" desc="Fields">

    private final ProjectionInfo ATS1977UTMZone19N;
    private final ProjectionInfo ATS1977UTMZone20N;
    private final ProjectionInfo Abidjan1987UTMZone29N;
    private final ProjectionInfo Abidjan1987UTMZone30N;
    private final ProjectionInfo AdindanUTMZone37N;
    private final ProjectionInfo AdindanUTMZone38N;
    private final ProjectionInfo AfgooyeUTMZone38N;
    private final ProjectionInfo AfgooyeUTMZone39N;
    private final ProjectionInfo AinelAbd1970UTMZone37N;
    private final ProjectionInfo AinelAbd1970UTMZone38N;
    private final ProjectionInfo AinelAbd1970UTMZone39N;
    private final ProjectionInfo AmericanSamoa1962UTMZone2S;
    private final ProjectionInfo AratuUTMZone22S;
    private final ProjectionInfo AratuUTMZone23S;
    private final ProjectionInfo AratuUTMZone24S;
    private final ProjectionInfo Arc1950UTMZone34S;
    private final ProjectionInfo Arc1950UTMZone35S;
    private final ProjectionInfo Arc1950UTMZone36S;
    private final ProjectionInfo Arc1960UTMZone35N;
    private final ProjectionInfo Arc1960UTMZone35S;
    private final ProjectionInfo Arc1960UTMZone36N;
    private final ProjectionInfo Arc1960UTMZone36S;
    private final ProjectionInfo Arc1960UTMZone37N;
    private final ProjectionInfo Arc1960UTMZone37S;
    private final ProjectionInfo AzoresCentral1995UTMZone26N;
    private final ProjectionInfo AzoresOriental1995UTMZone26N;
    private final ProjectionInfo BataviaUTMZone48S;
    private final ProjectionInfo BataviaUTMZone49S;
    private final ProjectionInfo BataviaUTMZone50S;
    private final ProjectionInfo BissauUTMZone28N;
    private final ProjectionInfo BogotaUTMZone17N;
    private final ProjectionInfo BogotaUTMZone18N;
    private final ProjectionInfo CSG1967UTMZone22N;
    private final ProjectionInfo CamacupaUTMZone32S;
    private final ProjectionInfo CamacupaUTMZone33S;
    private final ProjectionInfo CampoInchauspeUTM19S;
    private final ProjectionInfo CampoInchauspeUTM20S;
    private final ProjectionInfo CapeUTMZone34S;
    private final ProjectionInfo CapeUTMZone35S;
    private final ProjectionInfo CapeUTMZone36S;
    private final ProjectionInfo CarthageUTMZone32N;
    private final ProjectionInfo Combani1950UTMZone38S;
    private final ProjectionInfo Conakry1905UTMZone28N;
    private final ProjectionInfo Conakry1905UTMZone29N;
    private final ProjectionInfo CorregoAlegreUTMZone23S;
    private final ProjectionInfo CorregoAlegreUTMZone24S;
    private final ProjectionInfo DabolaUTMZone28N;
    private final ProjectionInfo DabolaUTMZone29N;
    private final ProjectionInfo Datum73UTMZone29N;
    private final ProjectionInfo DoualaUTMZone32N;
    private final ProjectionInfo ED1950ED77UTMZone38N;
    private final ProjectionInfo ED1950ED77UTMZone39N;
    private final ProjectionInfo ED1950ED77UTMZone40N;
    private final ProjectionInfo ED1950ED77UTMZone41N;
    private final ProjectionInfo ELD1979UTMZone32N;
    private final ProjectionInfo ELD1979UTMZone33N;
    private final ProjectionInfo ELD1979UTMZone34N;
    private final ProjectionInfo ELD1979UTMZone35N;
    private final ProjectionInfo ETRF1989UTMZone28N;
    private final ProjectionInfo ETRF1989UTMZone29N;
    private final ProjectionInfo ETRF1989UTMZone30N;
    private final ProjectionInfo ETRF1989UTMZone31N;
    private final ProjectionInfo ETRF1989UTMZone32N;
    private final ProjectionInfo ETRF1989UTMZone33N;
    private final ProjectionInfo ETRF1989UTMZone34N;
    private final ProjectionInfo ETRF1989UTMZone35N;
    private final ProjectionInfo ETRF1989UTMZone36N;
    private final ProjectionInfo ETRF1989UTMZone37N;
    private final ProjectionInfo ETRF1989UTMZone38N;
    private final ProjectionInfo ETRS1989UTMZone26N;
    private final ProjectionInfo ETRS1989UTMZone27N;
    private final ProjectionInfo ETRS1989UTMZone28N;
    private final ProjectionInfo ETRS1989UTMZone29N;
    private final ProjectionInfo ETRS1989UTMZone30N;
    private final ProjectionInfo ETRS1989UTMZone31N;
    private final ProjectionInfo ETRS1989UTMZone32N;
    private final ProjectionInfo ETRS1989UTMZone33N;
    private final ProjectionInfo ETRS1989UTMZone34N;
    private final ProjectionInfo ETRS1989UTMZone35N;
    private final ProjectionInfo ETRS1989UTMZone36N;
    private final ProjectionInfo ETRS1989UTMZone37N;
    private final ProjectionInfo ETRS1989UTMZone38N;
    private final ProjectionInfo ETRS1989UTMZone39N;
    private final ProjectionInfo EuropeanDatum1950UTMZone28N;
    private final ProjectionInfo EuropeanDatum1950UTMZone29N;
    private final ProjectionInfo EuropeanDatum1950UTMZone30N;
    private final ProjectionInfo EuropeanDatum1950UTMZone31N;
    private final ProjectionInfo EuropeanDatum1950UTMZone32N;
    private final ProjectionInfo EuropeanDatum1950UTMZone33N;
    private final ProjectionInfo EuropeanDatum1950UTMZone34N;
    private final ProjectionInfo EuropeanDatum1950UTMZone35N;
    private final ProjectionInfo EuropeanDatum1950UTMZone36N;
    private final ProjectionInfo EuropeanDatum1950UTMZone37N;
    private final ProjectionInfo EuropeanDatum1950UTMZone38N;
    private final ProjectionInfo FahudUTMZone39N;
    private final ProjectionInfo FahudUTMZone40N;
    private final ProjectionInfo FortDesaixUTMZone20N;
    private final ProjectionInfo FortMarigotUTMZone20N;
    private final ProjectionInfo GarouaUTMZone33N;
    private final ProjectionInfo GraciosaBaseSW1948UTMZone26N;
    private final ProjectionInfo GrandComorosUTMZone38S;
    private final ProjectionInfo HitoXVIII1963UTMZone19S;
    private final ProjectionInfo Hjorsey1955UTMZone26N;
    private final ProjectionInfo Hjorsey1955UTMZone27N;
    private final ProjectionInfo Hjorsey1955UTMZone28N;
    private final ProjectionInfo HongKong1980UTMZone49N;
    private final ProjectionInfo HongKong1980UTMZone50N;
    private final ProjectionInfo IGM1995UTMZone32N;
    private final ProjectionInfo IGM1995UTMZone33N;
    private final ProjectionInfo IGN53MareUTMZone58S;
    private final ProjectionInfo IGN56LifouUTMZone58S;
    private final ProjectionInfo IGN72GrandeTerreUTMZone58S;
    private final ProjectionInfo IGN72NukuHivaUTMZone7S;
    private final ProjectionInfo IRENET95UTMZone29N;
    private final ProjectionInfo Indian1954UTMZone46N;
    private final ProjectionInfo Indian1954UTMZone47N;
    private final ProjectionInfo Indian1954UTMZone48N;
    private final ProjectionInfo Indian1960UTMZone48N;
    private final ProjectionInfo Indian1960UTMZone49N;
    private final ProjectionInfo Indian1975UTMZone47N;
    private final ProjectionInfo Indian1975UTMZone48N;
    private final ProjectionInfo Indonesia1974UTMZone46N;
    private final ProjectionInfo Indonesia1974UTMZone46S;
    private final ProjectionInfo Indonesia1974UTMZone47N;
    private final ProjectionInfo Indonesia1974UTMZone47S;
    private final ProjectionInfo Indonesia1974UTMZone48N;
    private final ProjectionInfo Indonesia1974UTMZone48S;
    private final ProjectionInfo Indonesia1974UTMZone49N;
    private final ProjectionInfo Indonesia1974UTMZone49S;
    private final ProjectionInfo Indonesia1974UTMZone50N;
    private final ProjectionInfo Indonesia1974UTMZone50S;
    private final ProjectionInfo Indonesia1974UTMZone51N;
    private final ProjectionInfo Indonesia1974UTMZone51S;
    private final ProjectionInfo Indonesia1974UTMZone52N;
    private final ProjectionInfo Indonesia1974UTMZone52S;
    private final ProjectionInfo Indonesia1974UTMZone53N;
    private final ProjectionInfo Indonesia1974UTMZone53S;
    private final ProjectionInfo Indonesia1974UTMZone54S;
    private final ProjectionInfo JGD2000UTMZone51N;
    private final ProjectionInfo JGD2000UTMZone52N;
    private final ProjectionInfo JGD2000UTMZone53N;
    private final ProjectionInfo JGD2000UTMZone54N;
    private final ProjectionInfo JGD2000UTMZone55N;
    private final ProjectionInfo JGD2000UTMZone56N;
    private final ProjectionInfo K01949UTMZone42S;
    private final ProjectionInfo KertauUTMZone47N;
    private final ProjectionInfo KertauUTMZone48N;
    private final ProjectionInfo KousseriUTMZone33N;
    private final ProjectionInfo LaCanoaUTMZone18N;
    private final ProjectionInfo LaCanoaUTMZone19N;
    private final ProjectionInfo LaCanoaUTMZone20N;
    private final ProjectionInfo LaCanoaUTMZone21N;
    private final ProjectionInfo Locodjo1965UTMZone29N;
    private final ProjectionInfo Locodjo1965UTMZone30N;
    private final ProjectionInfo LomeUTMZone31N;
    private final ProjectionInfo MOP78UTMZone1S;
    private final ProjectionInfo Malongo1987UTMZone32S;
    private final ProjectionInfo Manoca1962UTMZone32N;
    private final ProjectionInfo MassawaUTMZone37N;
    private final ProjectionInfo MhastUTMZone32S;
    private final ProjectionInfo MinnaUTMZone31N;
    private final ProjectionInfo MinnaUTMZone32N;
    private final ProjectionInfo MoznetUTMZone36S;
    private final ProjectionInfo MoznetUTMZone37S;
    private final ProjectionInfo MporalokoUTMZone32N;
    private final ProjectionInfo MporalokoUTMZone32S;
    private final ProjectionInfo NAD1927BLMZone14N;
    private final ProjectionInfo NAD1927BLMZone15N;
    private final ProjectionInfo NAD1927BLMZone16N;
    private final ProjectionInfo NAD1927BLMZone17N;
    private final ProjectionInfo NAD1983HARNUTMZone11N;
    private final ProjectionInfo NAD1983HARNUTMZone12N;
    private final ProjectionInfo NAD1983HARNUTMZone13N;
    private final ProjectionInfo NAD1983HARNUTMZone18N;
    private final ProjectionInfo NAD1983HARNUTMZone2S;
    private final ProjectionInfo NAD1983HARNUTMZone4N;
    private final ProjectionInfo NAD1983HARNUTMZone5N;
    private final ProjectionInfo NEA74NoumeaUTMZone58S;
    private final ProjectionInfo NGNUTMZone38N;
    private final ProjectionInfo NGNUTMZone39N;
    private final ProjectionInfo NGO1948UTMZone32N;
    private final ProjectionInfo NGO1948UTMZone33N;
    private final ProjectionInfo NGO1948UTMZone34N;
    private final ProjectionInfo NGO1948UTMZone35N;
    private final ProjectionInfo NZGD1949UTMZone58S;
    private final ProjectionInfo NZGD1949UTMZone59S;
    private final ProjectionInfo NZGD1949UTMZone60S;
    private final ProjectionInfo NZGD2000UTMZone58S;
    private final ProjectionInfo NZGD2000UTMZone59S;
    private final ProjectionInfo NZGD2000UTMZone60S;
    private final ProjectionInfo Nahrwan1967UTMZone38N;
    private final ProjectionInfo Nahrwan1967UTMZone39N;
    private final ProjectionInfo Nahrwan1967UTMZone40N;
    private final ProjectionInfo Naparima1955UTMZone20N;
    private final ProjectionInfo Naparima1972UTMZone20N;
    private final ProjectionInfo NordSahara1959UTMZone29N;
    private final ProjectionInfo NordSahara1959UTMZone30N;
    private final ProjectionInfo NordSahara1959UTMZone31N;
    private final ProjectionInfo NordSahara1959UTMZone32N;
    private final ProjectionInfo ObservMeteorologico1939UTMZone25N;
    private final ProjectionInfo OldHawaiianUTMZone4N;
    private final ProjectionInfo OldHawaiianUTMZone5N;
    private final ProjectionInfo PDO1993UTMZone39N;
    private final ProjectionInfo PDO1993UTMZone40N;
    private final ProjectionInfo PointeNoireUTMZone32S;
    private final ProjectionInfo PortoSanto1936UTMZone28N;
    private final ProjectionInfo PortoSanto1995UTMZone28N;
    private final ProjectionInfo ProvSAmerDatumUTMZone17s;
    private final ProjectionInfo ProvSAmerDatumUTMZone18N;
    private final ProjectionInfo ProvSAmerDatumUTMZone18S;
    private final ProjectionInfo ProvSAmerDatumUTMZone19N;
    private final ProjectionInfo ProvSAmerDatumUTMZone19S;
    private final ProjectionInfo ProvSAmerDatumUTMZone20N;
    private final ProjectionInfo ProvSAmerDatumUTMZone20S;
    private final ProjectionInfo ProvSAmerDatumUTMZone21N;
    private final ProjectionInfo ProvSAmerDatumUTMZone22S;
    private final ProjectionInfo PuertoRicoUTMZone20N;
    private final ProjectionInfo Qornoq1927UTMZone22N;
    private final ProjectionInfo Qornoq1927UTMZone23N;
    private final ProjectionInfo REGVENUTMZone18N;
    private final ProjectionInfo REGVENUTMZone19N;
    private final ProjectionInfo REGVENUTMZone20N;
    private final ProjectionInfo RGFG1995UTMZone22N;
    private final ProjectionInfo RGR1992UTMZone40S;
    private final ProjectionInfo RRAF1991UTMZone20N;
    private final ProjectionInfo SIRGASUTMZone17N;
    private final ProjectionInfo SIRGASUTMZone17S;
    private final ProjectionInfo SIRGASUTMZone18N;
    private final ProjectionInfo SIRGASUTMZone18S;
    private final ProjectionInfo SIRGASUTMZone19N;
    private final ProjectionInfo SIRGASUTMZone19S;
    private final ProjectionInfo SIRGASUTMZone20N;
    private final ProjectionInfo SIRGASUTMZone20S;
    private final ProjectionInfo SIRGASUTMZone21N;
    private final ProjectionInfo SIRGASUTMZone21S;
    private final ProjectionInfo SIRGASUTMZone22N;
    private final ProjectionInfo SIRGASUTMZone22S;
    private final ProjectionInfo SIRGASUTMZone23S;
    private final ProjectionInfo SIRGASUTMZone24S;
    private final ProjectionInfo SIRGASUTMZone25S;
    private final ProjectionInfo ST71BelepUTMZone58S;
    private final ProjectionInfo ST84IledesPinsUTMZone58S;
    private final ProjectionInfo ST87OuveaUTMZone58S;
    private final ProjectionInfo SaintPierreetMiquelon1950UTMZone21N;
    private final ProjectionInfo SainteAnneUTMZone20N;
    private final ProjectionInfo SambojaUTMZone50S;
    private final ProjectionInfo SaoBrazUTMZone26N;
    private final ProjectionInfo SapperHill1943UTMZone20S;
    private final ProjectionInfo SapperHill1943UTMZone21S;
    private final ProjectionInfo SchwarzeckUTMZone33S;
    private final ProjectionInfo SelvagemGrande1938UTMZone28N;
    private final ProjectionInfo SierraLeone1968UTMZone28N;
    private final ProjectionInfo SierraLeone1968UTMZone29N;
    private final ProjectionInfo SouthAmerican1969UTMZone17S;
    private final ProjectionInfo SouthAmerican1969UTMZone18N;
    private final ProjectionInfo SouthAmerican1969UTMZone18S;
    private final ProjectionInfo SouthAmerican1969UTMZone19N;
    private final ProjectionInfo SouthAmerican1969UTMZone19S;
    private final ProjectionInfo SouthAmerican1969UTMZone20N;
    private final ProjectionInfo SouthAmerican1969UTMZone20S;
    private final ProjectionInfo SouthAmerican1969UTMZone21N;
    private final ProjectionInfo SouthAmerican1969UTMZone21S;
    private final ProjectionInfo SouthAmerican1969UTMZone22N;
    private final ProjectionInfo SouthAmerican1969UTMZone22S;
    private final ProjectionInfo SouthAmerican1969UTMZone23S;
    private final ProjectionInfo SouthAmerican1969UTMZone24S;
    private final ProjectionInfo SouthAmerican1969UTMZone25S;
    private final ProjectionInfo SudanUTMZone35N;
    private final ProjectionInfo SudanUTMZone36N;
    private final ProjectionInfo TahaaUTMZone5S;
    private final ProjectionInfo TahitiUTMZone6S;
    private final ProjectionInfo Tananarive1925UTMZone38S;
    private final ProjectionInfo Tananarive1925UTMZone39S;
    private final ProjectionInfo TeteUTMZone36S;
    private final ProjectionInfo TeteUTMZone37S;
    private final ProjectionInfo Timbalai1948UTMZone49N;
    private final ProjectionInfo Timbalai1948UTMZone50N;
    private final ProjectionInfo TokyoUTMZone51N;
    private final ProjectionInfo TokyoUTMZone52N;
    private final ProjectionInfo TokyoUTMZone53N;
    private final ProjectionInfo TokyoUTMZone54N;
    private final ProjectionInfo TokyoUTMZone55N;
    private final ProjectionInfo TokyoUTMZone56N;
    private final ProjectionInfo TrucialCoast1948UTMZone39N;
    private final ProjectionInfo TrucialCoast1948UTMZone40N;
    private final ProjectionInfo YemenNGN1996UTMZone38N;
    private final ProjectionInfo YemenNGN1996UTMZone39N;
    private final ProjectionInfo Yoff1972UTMZone28N;
    private final ProjectionInfo Zanderij1972UTMZone21N;

    //</editor-fold>


    //<editor-fold defaultstate="collapsed" desc="Constructor">

    /**
     * Creates a new instance of UtmOther
     */
    public UtmOther() {
        Abidjan1987UTMZone29N = ProjectionInfo.fromProj4String("+proj=utm +zone=29 +ellps=clrk80 +units=m +no_defs ").orElse(null);
        Abidjan1987UTMZone30N = ProjectionInfo.fromProj4String("+proj=utm +zone=30 +ellps=clrk80 +units=m +no_defs ").orElse(null);
        AdindanUTMZone37N = ProjectionInfo.fromProj4String("+proj=utm +zone=37 +ellps=clrk80 +units=m +no_defs ").orElse(null);
        AdindanUTMZone38N = ProjectionInfo.fromProj4String("+proj=utm +zone=38 +ellps=clrk80 +units=m +no_defs ").orElse(null);
        AfgooyeUTMZone38N = ProjectionInfo.fromProj4String("+proj=utm +zone=38 +ellps=krass +units=m +no_defs ").orElse(null);
        AfgooyeUTMZone39N = ProjectionInfo.fromProj4String("+proj=utm +zone=39 +ellps=krass +units=m +no_defs ").orElse(null);
        AinelAbd1970UTMZone37N = ProjectionInfo.fromProj4String("+proj=utm +zone=37 +ellps=intl +units=m +no_defs ").orElse(null);
        AinelAbd1970UTMZone38N = ProjectionInfo.fromProj4String("+proj=utm +zone=38 +ellps=intl +units=m +no_defs ").orElse(null);
        AinelAbd1970UTMZone39N = ProjectionInfo.fromProj4String("+proj=utm +zone=39 +ellps=intl +units=m +no_defs ").orElse(null);
        AmericanSamoa1962UTMZone2S = ProjectionInfo.fromProj4String("+proj=utm +zone=2 +south +ellps=clrk66 +units=m +no_defs ").orElse(null);
        AratuUTMZone22S = ProjectionInfo.fromProj4String("+proj=utm +zone=22 +south +ellps=intl +units=m +no_defs ").orElse(null);
        AratuUTMZone23S = ProjectionInfo.fromProj4String("+proj=utm +zone=23 +south +ellps=intl +units=m +no_defs ").orElse(null);
        AratuUTMZone24S = ProjectionInfo.fromProj4String("+proj=utm +zone=24 +south +ellps=intl +units=m +no_defs ").orElse(null);
        Arc1950UTMZone34S = ProjectionInfo.fromProj4String("+proj=utm +zone=34 +south +a=6378249.145 +b=6356514.966395495 +units=m +no_defs ").orElse(null);
        Arc1950UTMZone35S = ProjectionInfo.fromProj4String("+proj=utm +zone=35 +south +a=6378249.145 +b=6356514.966395495 +units=m +no_defs ").orElse(null);
        Arc1950UTMZone36S = ProjectionInfo.fromProj4String("+proj=utm +zone=36 +south +a=6378249.145 +b=6356514.966395495 +units=m +no_defs ").orElse(null);
        Arc1960UTMZone35N = ProjectionInfo.fromProj4String("+proj=utm +zone=35 +ellps=clrk80 +units=m +no_defs ").orElse(null);
        Arc1960UTMZone35S = ProjectionInfo.fromProj4String("+proj=utm +zone=35 +south +ellps=clrk80 +units=m +no_defs ").orElse(null);
        Arc1960UTMZone36N = ProjectionInfo.fromProj4String("+proj=utm +zone=36 +ellps=clrk80 +units=m +no_defs ").orElse(null);
        Arc1960UTMZone36S = ProjectionInfo.fromProj4String("+proj=utm +zone=36 +south +ellps=clrk80 +units=m +no_defs ").orElse(null);
        Arc1960UTMZone37N = ProjectionInfo.fromProj4String("+proj=utm +zone=37 +ellps=clrk80 +units=m +no_defs ").orElse(null);
        Arc1960UTMZone37S = ProjectionInfo.fromProj4String("+proj=utm +zone=37 +south +ellps=clrk80 +units=m +no_defs ").orElse(null);
        ATS1977UTMZone19N = ProjectionInfo.fromProj4String("+proj=utm +zone=19 +a=6378135 +b=6356750.304921594 +units=m +no_defs ").orElse(null);
        ATS1977UTMZone20N = ProjectionInfo.fromProj4String("+proj=utm +zone=20 +a=6378135 +b=6356750.304921594 +units=m +no_defs ").orElse(null);
        AzoresCentral1995UTMZone26N = ProjectionInfo.fromProj4String("+proj=utm +zone=26 +ellps=intl +units=m +no_defs ").orElse(null);
        AzoresOriental1995UTMZone26N = ProjectionInfo.fromProj4String("+proj=utm +zone=26 +ellps=intl +units=m +no_defs ").orElse(null);
        BataviaUTMZone48S = ProjectionInfo.fromProj4String("+proj=utm +zone=48 +south +ellps=bessel +units=m +no_defs ").orElse(null);
        BataviaUTMZone49S = ProjectionInfo.fromProj4String("+proj=utm +zone=49 +south +ellps=bessel +units=m +no_defs ").orElse(null);
        BataviaUTMZone50S = ProjectionInfo.fromProj4String("+proj=utm +zone=50 +south +ellps=bessel +units=m +no_defs ").orElse(null);
        BissauUTMZone28N = ProjectionInfo.fromProj4String("+proj=utm +zone=28 +ellps=intl +units=m +no_defs ").orElse(null);
        BogotaUTMZone17N = ProjectionInfo.fromProj4String("+proj=utm +zone=17 +ellps=intl +units=m +no_defs ").orElse(null);
        BogotaUTMZone18N = ProjectionInfo.fromProj4String("+proj=utm +zone=18 +ellps=intl +units=m +no_defs ").orElse(null);
        CamacupaUTMZone32S = ProjectionInfo.fromProj4String("+proj=utm +zone=32 +south +ellps=clrk80 +units=m +no_defs ").orElse(null);
        CamacupaUTMZone33S = ProjectionInfo.fromProj4String("+proj=utm +zone=33 +south +ellps=clrk80 +units=m +no_defs ").orElse(null);
        CampoInchauspeUTM19S = ProjectionInfo.fromProj4String("+proj=utm +zone=19 +south +ellps=intl +units=m +no_defs ").orElse(null);
        CampoInchauspeUTM20S = ProjectionInfo.fromProj4String("+proj=utm +zone=20 +south +ellps=intl +units=m +no_defs ").orElse(null);
        CapeUTMZone34S = ProjectionInfo.fromProj4String("+proj=utm +zone=34 +south +a=6378249.145 +b=6356514.966395495 +units=m +no_defs ").orElse(null);
        CapeUTMZone35S = ProjectionInfo.fromProj4String("+proj=utm +zone=35 +south +a=6378249.145 +b=6356514.966395495 +units=m +no_defs ").orElse(null);
        CapeUTMZone36S = ProjectionInfo.fromProj4String("+proj=utm +zone=36 +south +a=6378249.145 +b=6356514.966395495 +units=m +no_defs ").orElse(null);
        CarthageUTMZone32N = ProjectionInfo.fromProj4String("+proj=utm +zone=32 +a=6378249.2 +b=6356514.999904194 +units=m +no_defs ").orElse(null);
        Combani1950UTMZone38S = ProjectionInfo.fromProj4String("+proj=utm +zone=38 +south +ellps=intl +units=m +no_defs ").orElse(null);
        Conakry1905UTMZone28N = ProjectionInfo.fromProj4String("+proj=utm +zone=28 +a=6378249.2 +b=6356514.999904194 +units=m +no_defs ").orElse(null);
        Conakry1905UTMZone29N = ProjectionInfo.fromProj4String("+proj=utm +zone=29 +a=6378249.2 +b=6356514.999904194 +units=m +no_defs ").orElse(null);
        CorregoAlegreUTMZone23S = ProjectionInfo.fromProj4String("+proj=utm +zone=23 +south +ellps=intl +units=m +no_defs ").orElse(null);
        CorregoAlegreUTMZone24S = ProjectionInfo.fromProj4String("+proj=utm +zone=24 +south +ellps=intl +units=m +no_defs ").orElse(null);
        CSG1967UTMZone22N = ProjectionInfo.fromProj4String("+proj=utm +zone=22 +ellps=intl +units=m +no_defs ").orElse(null);
        DabolaUTMZone28N = ProjectionInfo.fromProj4String("+proj=utm +zone=28 +ellps=clrk80 +units=m +no_defs ").orElse(null);
        DabolaUTMZone29N = ProjectionInfo.fromProj4String("+proj=utm +zone=29 +ellps=clrk80 +units=m +no_defs ").orElse(null);
        Datum73UTMZone29N = ProjectionInfo.fromProj4String("+proj=utm +zone=29 +ellps=intl +units=m +no_defs ").orElse(null);
        DoualaUTMZone32N = ProjectionInfo.fromProj4String("+proj=utm +zone=32 +a=6378249.2 +b=6356514.999904194 +units=m +no_defs ").orElse(null);
        ED1950ED77UTMZone38N = ProjectionInfo.fromProj4String("+proj=utm +zone=38 +ellps=intl +units=m +no_defs ").orElse(null);
        ED1950ED77UTMZone39N = ProjectionInfo.fromProj4String("+proj=utm +zone=39 +ellps=intl +units=m +no_defs ").orElse(null);
        ED1950ED77UTMZone40N = ProjectionInfo.fromProj4String("+proj=utm +zone=40 +ellps=intl +units=m +no_defs ").orElse(null);
        ED1950ED77UTMZone41N = ProjectionInfo.fromProj4String("+proj=utm +zone=41 +ellps=intl +units=m +no_defs ").orElse(null);
        ELD1979UTMZone32N = ProjectionInfo.fromProj4String("+proj=utm +zone=32 +ellps=intl +units=m +no_defs ").orElse(null);
        ELD1979UTMZone33N = ProjectionInfo.fromProj4String("+proj=utm +zone=33 +ellps=intl +units=m +no_defs ").orElse(null);
        ELD1979UTMZone34N = ProjectionInfo.fromProj4String("+proj=utm +zone=34 +ellps=intl +units=m +no_defs ").orElse(null);
        ELD1979UTMZone35N = ProjectionInfo.fromProj4String("+proj=utm +zone=35 +ellps=intl +units=m +no_defs ").orElse(null);
        ETRF1989UTMZone28N = ProjectionInfo.fromProj4String("+proj=utm +zone=28 +ellps=WGS84 +units=m +no_defs ").orElse(null);
        ETRF1989UTMZone29N = ProjectionInfo.fromProj4String("+proj=utm +zone=29 +ellps=WGS84 +units=m +no_defs ").orElse(null);
        ETRF1989UTMZone30N = ProjectionInfo.fromProj4String("+proj=utm +zone=30 +ellps=WGS84 +units=m +no_defs ").orElse(null);
        ETRF1989UTMZone31N = ProjectionInfo.fromProj4String("+proj=utm +zone=31 +ellps=WGS84 +units=m +no_defs ").orElse(null);
        ETRF1989UTMZone32N = ProjectionInfo.fromProj4String("+proj=utm +zone=32 +ellps=WGS84 +units=m +no_defs ").orElse(null);
        ETRF1989UTMZone33N = ProjectionInfo.fromProj4String("+proj=utm +zone=33 +ellps=WGS84 +units=m +no_defs ").orElse(null);
        ETRF1989UTMZone34N = ProjectionInfo.fromProj4String("+proj=utm +zone=34 +ellps=WGS84 +units=m +no_defs ").orElse(null);
        ETRF1989UTMZone35N = ProjectionInfo.fromProj4String("+proj=utm +zone=35 +ellps=WGS84 +units=m +no_defs ").orElse(null);
        ETRF1989UTMZone36N = ProjectionInfo.fromProj4String("+proj=utm +zone=36 +ellps=WGS84 +units=m +no_defs ").orElse(null);
        ETRF1989UTMZone37N = ProjectionInfo.fromProj4String("+proj=utm +zone=37 +ellps=WGS84 +units=m +no_defs ").orElse(null);
        ETRF1989UTMZone38N = ProjectionInfo.fromProj4String("+proj=utm +zone=38 +ellps=WGS84 +units=m +no_defs ").orElse(null);
        ETRS1989UTMZone26N = ProjectionInfo.fromProj4String("+proj=utm +zone=26 +ellps=GRS80 +units=m +no_defs ").orElse(null);
        ETRS1989UTMZone27N = ProjectionInfo.fromProj4String("+proj=utm +zone=27 +ellps=GRS80 +units=m +no_defs ").orElse(null);
        ETRS1989UTMZone28N = ProjectionInfo.fromProj4String("+proj=utm +zone=28 +ellps=GRS80 +units=m +no_defs ").orElse(null);
        ETRS1989UTMZone29N = ProjectionInfo.fromProj4String("+proj=utm +zone=29 +ellps=GRS80 +units=m +no_defs ").orElse(null);
        ETRS1989UTMZone30N = ProjectionInfo.fromProj4String("+proj=utm +zone=30 +ellps=GRS80 +units=m +no_defs ").orElse(null);
        ETRS1989UTMZone31N = ProjectionInfo.fromProj4String("+proj=utm +zone=31 +ellps=GRS80 +units=m +no_defs ").orElse(null);
        ETRS1989UTMZone32N = ProjectionInfo.fromProj4String("+proj=utm +zone=32 +ellps=GRS80 +units=m +no_defs ").orElse(null);
        ETRS1989UTMZone33N = ProjectionInfo.fromProj4String("+proj=utm +zone=33 +ellps=GRS80 +units=m +no_defs ").orElse(null);
        ETRS1989UTMZone34N = ProjectionInfo.fromProj4String("+proj=utm +zone=34 +ellps=GRS80 +units=m +no_defs ").orElse(null);
        ETRS1989UTMZone35N = ProjectionInfo.fromProj4String("+proj=utm +zone=35 +ellps=GRS80 +units=m +no_defs ").orElse(null);
        ETRS1989UTMZone36N = ProjectionInfo.fromProj4String("+proj=utm +zone=36 +ellps=GRS80 +units=m +no_defs ").orElse(null);
        ETRS1989UTMZone37N = ProjectionInfo.fromProj4String("+proj=utm +zone=37 +ellps=GRS80 +units=m +no_defs ").orElse(null);
        ETRS1989UTMZone38N = ProjectionInfo.fromProj4String("+proj=utm +zone=38 +ellps=GRS80 +units=m +no_defs ").orElse(null);
        ETRS1989UTMZone39N = ProjectionInfo.fromProj4String("+proj=utm +zone=39 +ellps=GRS80 +units=m +no_defs ").orElse(null);
        EuropeanDatum1950UTMZone28N = ProjectionInfo.fromProj4String("+proj=utm +zone=28 +ellps=intl +units=m +no_defs ").orElse(null);
        EuropeanDatum1950UTMZone29N = ProjectionInfo.fromProj4String("+proj=utm +zone=29 +ellps=intl +units=m +no_defs ").orElse(null);
        EuropeanDatum1950UTMZone30N = ProjectionInfo.fromProj4String("+proj=utm +zone=30 +ellps=intl +units=m +no_defs ").orElse(null);
        EuropeanDatum1950UTMZone31N = ProjectionInfo.fromProj4String("+proj=utm +zone=31 +ellps=intl +units=m +no_defs ").orElse(null);
        EuropeanDatum1950UTMZone32N = ProjectionInfo.fromProj4String("+proj=utm +zone=32 +ellps=intl +units=m +no_defs ").orElse(null);
        EuropeanDatum1950UTMZone33N = ProjectionInfo.fromProj4String("+proj=utm +zone=33 +ellps=intl +units=m +no_defs ").orElse(null);
        EuropeanDatum1950UTMZone34N = ProjectionInfo.fromProj4String("+proj=utm +zone=34 +ellps=intl +units=m +no_defs ").orElse(null);
        EuropeanDatum1950UTMZone35N = ProjectionInfo.fromProj4String("+proj=utm +zone=35 +ellps=intl +units=m +no_defs ").orElse(null);
        EuropeanDatum1950UTMZone36N = ProjectionInfo.fromProj4String("+proj=utm +zone=36 +ellps=intl +units=m +no_defs ").orElse(null);
        EuropeanDatum1950UTMZone37N = ProjectionInfo.fromProj4String("+proj=utm +zone=37 +ellps=intl +units=m +no_defs ").orElse(null);
        EuropeanDatum1950UTMZone38N = ProjectionInfo.fromProj4String("+proj=utm +zone=38 +ellps=intl +units=m +no_defs ").orElse(null);
        FahudUTMZone39N = ProjectionInfo.fromProj4String("+proj=utm +zone=39 +ellps=clrk80 +units=m +no_defs ").orElse(null);
        FahudUTMZone40N = ProjectionInfo.fromProj4String("+proj=utm +zone=40 +ellps=clrk80 +units=m +no_defs ").orElse(null);
        FortDesaixUTMZone20N = ProjectionInfo.fromProj4String("+proj=utm +zone=20 +ellps=intl +units=m +no_defs ").orElse(null);
        FortMarigotUTMZone20N = ProjectionInfo.fromProj4String("+proj=utm +zone=20 +ellps=intl +units=m +no_defs ").orElse(null);
        GarouaUTMZone33N = ProjectionInfo.fromProj4String("+proj=utm +zone=33 +a=6378249.2 +b=6356514.999904194 +units=m +no_defs ").orElse(null);
        GraciosaBaseSW1948UTMZone26N = ProjectionInfo.fromProj4String("+proj=utm +zone=26 +ellps=intl +units=m +no_defs ").orElse(null);
        GrandComorosUTMZone38S = ProjectionInfo.fromProj4String("+proj=utm +zone=38 +south +ellps=intl +units=m +no_defs ").orElse(null);
        HitoXVIII1963UTMZone19S = ProjectionInfo.fromProj4String("+proj=utm +zone=19 +south +ellps=intl +units=m +no_defs ").orElse(null);
        Hjorsey1955UTMZone26N = ProjectionInfo.fromProj4String("+proj=utm +zone=26 +ellps=intl +units=m +no_defs ").orElse(null);
        Hjorsey1955UTMZone27N = ProjectionInfo.fromProj4String("+proj=utm +zone=27 +ellps=intl +units=m +no_defs ").orElse(null);
        Hjorsey1955UTMZone28N = ProjectionInfo.fromProj4String("+proj=utm +zone=28 +ellps=intl +units=m +no_defs ").orElse(null);
        HongKong1980UTMZone49N = ProjectionInfo.fromProj4String("+proj=utm +zone=49 +ellps=intl +units=m +no_defs ").orElse(null);
        HongKong1980UTMZone50N = ProjectionInfo.fromProj4String("+proj=utm +zone=50 +ellps=intl +units=m +no_defs ").orElse(null);
        IGM1995UTMZone32N = ProjectionInfo.fromProj4String("+proj=utm +zone=32 +ellps=WGS84 +units=m +no_defs ").orElse(null);
        IGM1995UTMZone33N = ProjectionInfo.fromProj4String("+proj=utm +zone=33 +ellps=WGS84 +units=m +no_defs ").orElse(null);
        IGN53MareUTMZone58S = ProjectionInfo.fromProj4String("+proj=utm +zone=58 +south +ellps=intl +units=m +no_defs ").orElse(null);
        IGN56LifouUTMZone58S = ProjectionInfo.fromProj4String("+proj=utm +zone=58 +south +ellps=intl +units=m +no_defs ").orElse(null);
        IGN72GrandeTerreUTMZone58S = ProjectionInfo.fromProj4String("+proj=utm +zone=58 +south +ellps=intl +units=m +no_defs ").orElse(null);
        IGN72NukuHivaUTMZone7S = ProjectionInfo.fromProj4String("+proj=utm +zone=7 +south +ellps=intl +units=m +no_defs ").orElse(null);
        Indian1954UTMZone46N = ProjectionInfo.fromProj4String("+proj=utm +zone=46 +a=6377276.345 +b=6356075.41314024 +units=m +no_defs ").orElse(null);
        Indian1954UTMZone47N = ProjectionInfo.fromProj4String("+proj=utm +zone=47 +a=6377276.345 +b=6356075.41314024 +units=m +no_defs ").orElse(null);
        Indian1954UTMZone48N = ProjectionInfo.fromProj4String("+proj=utm +zone=48 +a=6377276.345 +b=6356075.41314024 +units=m +no_defs ").orElse(null);
        Indian1960UTMZone48N = ProjectionInfo.fromProj4String("+proj=utm +zone=48 +a=6377276.345 +b=6356075.41314024 +units=m +no_defs ").orElse(null);
        Indian1960UTMZone49N = ProjectionInfo.fromProj4String("+proj=utm +zone=49 +a=6377276.345 +b=6356075.41314024 +units=m +no_defs ").orElse(null);
        Indian1975UTMZone47N = ProjectionInfo.fromProj4String("+proj=utm +zone=47 +a=6377276.345 +b=6356075.41314024 +units=m +no_defs ").orElse(null);
        Indian1975UTMZone48N = ProjectionInfo.fromProj4String("+proj=utm +zone=48 +a=6377276.345 +b=6356075.41314024 +units=m +no_defs ").orElse(null);
        Indonesia1974UTMZone46N = ProjectionInfo.fromProj4String("+proj=utm +zone=46 +a=6378160 +b=6356774.50408554 +units=m +no_defs ").orElse(null);
        Indonesia1974UTMZone46S = ProjectionInfo.fromProj4String("+proj=utm +zone=46 +south +a=6378160 +b=6356774.50408554 +units=m +no_defs ").orElse(null);
        Indonesia1974UTMZone47N = ProjectionInfo.fromProj4String("+proj=utm +zone=47 +a=6378160 +b=6356774.50408554 +units=m +no_defs ").orElse(null);
        Indonesia1974UTMZone47S = ProjectionInfo.fromProj4String("+proj=utm +zone=47 +south +a=6378160 +b=6356774.50408554 +units=m +no_defs ").orElse(null);
        Indonesia1974UTMZone48N = ProjectionInfo.fromProj4String("+proj=utm +zone=48 +a=6378160 +b=6356774.50408554 +units=m +no_defs ").orElse(null);
        Indonesia1974UTMZone48S = ProjectionInfo.fromProj4String("+proj=utm +zone=48 +south +a=6378160 +b=6356774.50408554 +units=m +no_defs ").orElse(null);
        Indonesia1974UTMZone49N = ProjectionInfo.fromProj4String("+proj=utm +zone=49 +a=6378160 +b=6356774.50408554 +units=m +no_defs ").orElse(null);
        Indonesia1974UTMZone49S = ProjectionInfo.fromProj4String("+proj=utm +zone=49 +south +a=6378160 +b=6356774.50408554 +units=m +no_defs ").orElse(null);
        Indonesia1974UTMZone50N = ProjectionInfo.fromProj4String("+proj=utm +zone=50 +a=6378160 +b=6356774.50408554 +units=m +no_defs ").orElse(null);
        Indonesia1974UTMZone50S = ProjectionInfo.fromProj4String("+proj=utm +zone=50 +south +a=6378160 +b=6356774.50408554 +units=m +no_defs ").orElse(null);
        Indonesia1974UTMZone51N = ProjectionInfo.fromProj4String("+proj=utm +zone=51 +a=6378160 +b=6356774.50408554 +units=m +no_defs ").orElse(null);
        Indonesia1974UTMZone51S = ProjectionInfo.fromProj4String("+proj=utm +zone=51 +south +a=6378160 +b=6356774.50408554 +units=m +no_defs ").orElse(null);
        Indonesia1974UTMZone52N = ProjectionInfo.fromProj4String("+proj=utm +zone=52 +a=6378160 +b=6356774.50408554 +units=m +no_defs ").orElse(null);
        Indonesia1974UTMZone52S = ProjectionInfo.fromProj4String("+proj=utm +zone=52 +south +a=6378160 +b=6356774.50408554 +units=m +no_defs ").orElse(null);
        Indonesia1974UTMZone53N = ProjectionInfo.fromProj4String("+proj=utm +zone=53 +a=6378160 +b=6356774.50408554 +units=m +no_defs ").orElse(null);
        Indonesia1974UTMZone53S = ProjectionInfo.fromProj4String("+proj=utm +zone=53 +south +a=6378160 +b=6356774.50408554 +units=m +no_defs ").orElse(null);
        Indonesia1974UTMZone54S = ProjectionInfo.fromProj4String("+proj=utm +zone=54 +south +a=6378160 +b=6356774.50408554 +units=m +no_defs ").orElse(null);
        IRENET95UTMZone29N = ProjectionInfo.fromProj4String("+proj=utm +zone=29 +ellps=GRS80 +units=m +no_defs ").orElse(null);
        JGD2000UTMZone51N = ProjectionInfo.fromProj4String("+proj=utm +zone=51 +ellps=GRS80 +units=m +no_defs ").orElse(null);
        JGD2000UTMZone52N = ProjectionInfo.fromProj4String("+proj=utm +zone=52 +ellps=GRS80 +units=m +no_defs ").orElse(null);
        JGD2000UTMZone53N = ProjectionInfo.fromProj4String("+proj=utm +zone=53 +ellps=GRS80 +units=m +no_defs ").orElse(null);
        JGD2000UTMZone54N = ProjectionInfo.fromProj4String("+proj=utm +zone=54 +ellps=GRS80 +units=m +no_defs ").orElse(null);
        JGD2000UTMZone55N = ProjectionInfo.fromProj4String("+proj=utm +zone=55 +ellps=GRS80 +units=m +no_defs ").orElse(null);
        JGD2000UTMZone56N = ProjectionInfo.fromProj4String("+proj=utm +zone=56 +ellps=GRS80 +units=m +no_defs ").orElse(null);
        K01949UTMZone42S = ProjectionInfo.fromProj4String("+proj=utm +zone=42 +south +ellps=intl +units=m +no_defs ").orElse(null);
        KertauUTMZone47N = ProjectionInfo.fromProj4String("+proj=utm +zone=47 +a=6377304.063 +b=6356103.038993155 +units=m +no_defs ").orElse(null);
        KertauUTMZone48N = ProjectionInfo.fromProj4String("+proj=utm +zone=48 +a=6377304.063 +b=6356103.038993155 +units=m +no_defs ").orElse(null);
        KousseriUTMZone33N = ProjectionInfo.fromProj4String("+proj=utm +zone=33 +ellps=clrk80 +units=m +no_defs ").orElse(null);
        LaCanoaUTMZone18N = ProjectionInfo.fromProj4String("+proj=utm +zone=18 +ellps=intl +units=m +no_defs ").orElse(null);
        LaCanoaUTMZone19N = ProjectionInfo.fromProj4String("+proj=utm +zone=19 +ellps=intl +units=m +no_defs ").orElse(null);
        LaCanoaUTMZone20N = ProjectionInfo.fromProj4String("+proj=utm +zone=20 +ellps=intl +units=m +no_defs ").orElse(null);
        LaCanoaUTMZone21N = ProjectionInfo.fromProj4String("+proj=utm +zone=21 +ellps=intl +units=m +no_defs ").orElse(null);
        Locodjo1965UTMZone29N = ProjectionInfo.fromProj4String("+proj=utm +zone=29 +ellps=clrk80 +units=m +no_defs ").orElse(null);
        Locodjo1965UTMZone30N = ProjectionInfo.fromProj4String("+proj=utm +zone=30 +ellps=clrk80 +units=m +no_defs ").orElse(null);
        LomeUTMZone31N = ProjectionInfo.fromProj4String("+proj=utm +zone=31 +a=6378249.2 +b=6356514.999904194 +units=m +no_defs ").orElse(null);
        Malongo1987UTMZone32S = ProjectionInfo.fromProj4String("+proj=utm +zone=32 +south +ellps=intl +units=m +no_defs ").orElse(null);
        Manoca1962UTMZone32N = ProjectionInfo.fromProj4String("+proj=utm +zone=32 +a=6378249.2 +b=6356514.999904194 +units=m +no_defs ").orElse(null);
        MassawaUTMZone37N = ProjectionInfo.fromProj4String("+proj=utm +zone=37 +ellps=bessel +units=m +no_defs ").orElse(null);
        MhastUTMZone32S = ProjectionInfo.fromProj4String("+proj=utm +zone=32 +south +ellps=intl +units=m +no_defs ").orElse(null);
        MinnaUTMZone31N = ProjectionInfo.fromProj4String("+proj=utm +zone=31 +ellps=clrk80 +units=m +no_defs ").orElse(null);
        MinnaUTMZone32N = ProjectionInfo.fromProj4String("+proj=utm +zone=32 +ellps=clrk80 +units=m +no_defs ").orElse(null);
        MOP78UTMZone1S = ProjectionInfo.fromProj4String("+proj=utm +zone=1 +south +ellps=intl +units=m +no_defs ").orElse(null);
        MoznetUTMZone36S = ProjectionInfo.fromProj4String("+proj=utm +zone=36 +south +ellps=WGS84 +units=m +no_defs ").orElse(null);
        MoznetUTMZone37S = ProjectionInfo.fromProj4String("+proj=utm +zone=37 +south +ellps=WGS84 +units=m +no_defs ").orElse(null);
        MporalokoUTMZone32N = ProjectionInfo.fromProj4String("+proj=utm +zone=32 +a=6378249.2 +b=6356514.999904194 +units=m +no_defs ").orElse(null);
        MporalokoUTMZone32S = ProjectionInfo.fromProj4String("+proj=utm +zone=32 +south +a=6378249.2 +b=6356514.999904194 +units=m +no_defs ").orElse(null);
        NAD1927BLMZone14N = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-99 +k=0.999600 +x_0=500000.0000000002 +y_0=0 +ellps=clrk66 +datum=NAD27 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
        NAD1927BLMZone15N = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-93 +k=0.999600 +x_0=500000.0000000002 +y_0=0 +ellps=clrk66 +datum=NAD27 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
        NAD1927BLMZone16N = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-87 +k=0.999600 +x_0=500000.0000000002 +y_0=0 +ellps=clrk66 +datum=NAD27 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
        NAD1927BLMZone17N = ProjectionInfo.fromProj4String("+proj=tmerc +lat_0=0 +lon_0=-81 +k=0.999600 +x_0=500000.0000000002 +y_0=0 +ellps=clrk66 +datum=NAD27 +to_meter=0.3048006096012192 +no_defs ").orElse(null);
        NAD1983HARNUTMZone11N = ProjectionInfo.fromProj4String("+proj=utm +zone=11 +ellps=GRS80 +units=m +no_defs ").orElse(null);
        NAD1983HARNUTMZone12N = ProjectionInfo.fromProj4String("+proj=utm +zone=12 +ellps=GRS80 +units=m +no_defs ").orElse(null);
        NAD1983HARNUTMZone13N = ProjectionInfo.fromProj4String("+proj=utm +zone=13 +ellps=GRS80 +units=m +no_defs ").orElse(null);
        NAD1983HARNUTMZone18N = ProjectionInfo.fromProj4String("+proj=utm +zone=18 +ellps=GRS80 +units=m +no_defs ").orElse(null);
        NAD1983HARNUTMZone2S = ProjectionInfo.fromProj4String("+proj=utm +zone=2 +south +ellps=GRS80 +units=m +no_defs ").orElse(null);
        NAD1983HARNUTMZone4N = ProjectionInfo.fromProj4String("+proj=utm +zone=4 +ellps=GRS80 +units=m +no_defs ").orElse(null);
        NAD1983HARNUTMZone5N = ProjectionInfo.fromProj4String("+proj=utm +zone=5 +ellps=GRS80 +units=m +no_defs ").orElse(null);
        Nahrwan1967UTMZone38N = ProjectionInfo.fromProj4String("+proj=utm +zone=38 +ellps=clrk80 +units=m +no_defs ").orElse(null);
        Nahrwan1967UTMZone39N = ProjectionInfo.fromProj4String("+proj=utm +zone=39 +ellps=clrk80 +units=m +no_defs ").orElse(null);
        Nahrwan1967UTMZone40N = ProjectionInfo.fromProj4String("+proj=utm +zone=40 +ellps=clrk80 +units=m +no_defs ").orElse(null);
        Naparima1955UTMZone20N = ProjectionInfo.fromProj4String("+proj=utm +zone=20 +ellps=intl +units=m +no_defs ").orElse(null);
        Naparima1972UTMZone20N = ProjectionInfo.fromProj4String("+proj=utm +zone=20 +ellps=intl +units=m +no_defs ").orElse(null);
        NEA74NoumeaUTMZone58S = ProjectionInfo.fromProj4String("+proj=utm +zone=58 +south +ellps=intl +units=m +no_defs ").orElse(null);
        NGNUTMZone38N = ProjectionInfo.fromProj4String("+proj=utm +zone=38 +ellps=WGS84 +units=m +no_defs ").orElse(null);
        NGNUTMZone39N = ProjectionInfo.fromProj4String("+proj=utm +zone=39 +ellps=WGS84 +units=m +no_defs ").orElse(null);
        NGO1948UTMZone32N = ProjectionInfo.fromProj4String("+proj=utm +zone=32 +a=6377492.018 +b=6356173.508712696 +units=m +no_defs ").orElse(null);
        NGO1948UTMZone33N = ProjectionInfo.fromProj4String("+proj=utm +zone=33 +a=6377492.018 +b=6356173.508712696 +units=m +no_defs ").orElse(null);
        NGO1948UTMZone34N = ProjectionInfo.fromProj4String("+proj=utm +zone=34 +a=6377492.018 +b=6356173.508712696 +units=m +no_defs ").orElse(null);
        NGO1948UTMZone35N = ProjectionInfo.fromProj4String("+proj=utm +zone=35 +a=6377492.018 +b=6356173.508712696 +units=m +no_defs ").orElse(null);
        NordSahara1959UTMZone29N = ProjectionInfo.fromProj4String("+proj=utm +zone=29 +ellps=clrk80 +units=m +no_defs ").orElse(null);
        NordSahara1959UTMZone30N = ProjectionInfo.fromProj4String("+proj=utm +zone=30 +ellps=clrk80 +units=m +no_defs ").orElse(null);
        NordSahara1959UTMZone31N = ProjectionInfo.fromProj4String("+proj=utm +zone=31 +ellps=clrk80 +units=m +no_defs ").orElse(null);
        NordSahara1959UTMZone32N = ProjectionInfo.fromProj4String("+proj=utm +zone=32 +ellps=clrk80 +units=m +no_defs ").orElse(null);
        NZGD1949UTMZone58S = ProjectionInfo.fromProj4String("+proj=utm +zone=58 +south +ellps=intl +units=m +no_defs ").orElse(null);
        NZGD1949UTMZone59S = ProjectionInfo.fromProj4String("+proj=utm +zone=59 +south +ellps=intl +units=m +no_defs ").orElse(null);
        NZGD1949UTMZone60S = ProjectionInfo.fromProj4String("+proj=utm +zone=60 +south +ellps=intl +units=m +no_defs ").orElse(null);
        NZGD2000UTMZone58S = ProjectionInfo.fromProj4String("+proj=utm +zone=58 +south +ellps=GRS80 +units=m +no_defs ").orElse(null);
        NZGD2000UTMZone59S = ProjectionInfo.fromProj4String("+proj=utm +zone=59 +south +ellps=GRS80 +units=m +no_defs ").orElse(null);
        NZGD2000UTMZone60S = ProjectionInfo.fromProj4String("+proj=utm +zone=60 +south +ellps=GRS80 +units=m +no_defs ").orElse(null);
        ObservMeteorologico1939UTMZone25N = ProjectionInfo.fromProj4String("+proj=utm +zone=25 +ellps=intl +units=m +no_defs ").orElse(null);
        OldHawaiianUTMZone4N = ProjectionInfo.fromProj4String("+proj=utm +zone=4 +ellps=clrk66 +units=m +no_defs ").orElse(null);
        OldHawaiianUTMZone5N = ProjectionInfo.fromProj4String("+proj=utm +zone=5 +ellps=clrk66 +units=m +no_defs ").orElse(null);
        PDO1993UTMZone39N = ProjectionInfo.fromProj4String("+proj=utm +zone=39 +ellps=clrk80 +units=m +no_defs ").orElse(null);
        PDO1993UTMZone40N = ProjectionInfo.fromProj4String("+proj=utm +zone=40 +ellps=clrk80 +units=m +no_defs ").orElse(null);
        PointeNoireUTMZone32S = ProjectionInfo.fromProj4String("+proj=utm +zone=32 +south +a=6378249.2 +b=6356514.999904194 +units=m +no_defs ").orElse(null);
        PortoSanto1936UTMZone28N = ProjectionInfo.fromProj4String("+proj=utm +zone=28 +ellps=intl +units=m +no_defs ").orElse(null);
        PortoSanto1995UTMZone28N = ProjectionInfo.fromProj4String("+proj=utm +zone=28 +ellps=intl +units=m +no_defs ").orElse(null);
        ProvSAmerDatumUTMZone17s = ProjectionInfo.fromProj4String("+proj=utm +zone=17 +south +ellps=intl +units=m +no_defs ").orElse(null);
        ProvSAmerDatumUTMZone18N = ProjectionInfo.fromProj4String("+proj=utm +zone=18 +ellps=intl +units=m +no_defs ").orElse(null);
        ProvSAmerDatumUTMZone18S = ProjectionInfo.fromProj4String("+proj=utm +zone=18 +south +ellps=intl +units=m +no_defs ").orElse(null);
        ProvSAmerDatumUTMZone19N = ProjectionInfo.fromProj4String("+proj=utm +zone=19 +ellps=intl +units=m +no_defs ").orElse(null);
        ProvSAmerDatumUTMZone19S = ProjectionInfo.fromProj4String("+proj=utm +zone=19 +south +ellps=intl +units=m +no_defs ").orElse(null);
        ProvSAmerDatumUTMZone20N = ProjectionInfo.fromProj4String("+proj=utm +zone=20 +ellps=intl +units=m +no_defs ").orElse(null);
        ProvSAmerDatumUTMZone20S = ProjectionInfo.fromProj4String("+proj=utm +zone=20 +south +ellps=intl +units=m +no_defs ").orElse(null);
        ProvSAmerDatumUTMZone21N = ProjectionInfo.fromProj4String("+proj=utm +zone=21 +ellps=intl +units=m +no_defs ").orElse(null);
        ProvSAmerDatumUTMZone22S = ProjectionInfo.fromProj4String("+proj=utm +zone=22 +south +ellps=intl +units=m +no_defs ").orElse(null);
        PuertoRicoUTMZone20N = ProjectionInfo.fromProj4String("+proj=utm +zone=20 +ellps=clrk66 +units=m +no_defs ").orElse(null);
        Qornoq1927UTMZone22N = ProjectionInfo.fromProj4String("+proj=utm +zone=22 +ellps=intl +units=m +no_defs ").orElse(null);
        Qornoq1927UTMZone23N = ProjectionInfo.fromProj4String("+proj=utm +zone=23 +ellps=intl +units=m +no_defs ").orElse(null);
        REGVENUTMZone18N = ProjectionInfo.fromProj4String("+proj=utm +zone=18 +ellps=GRS80 +units=m +no_defs ").orElse(null);
        REGVENUTMZone19N = ProjectionInfo.fromProj4String("+proj=utm +zone=19 +ellps=GRS80 +units=m +no_defs ").orElse(null);
        REGVENUTMZone20N = ProjectionInfo.fromProj4String("+proj=utm +zone=20 +ellps=GRS80 +units=m +no_defs ").orElse(null);
        RGFG1995UTMZone22N = ProjectionInfo.fromProj4String("+proj=utm +zone=22 +ellps=GRS80 +units=m +no_defs ").orElse(null);
        RGR1992UTMZone40S = ProjectionInfo.fromProj4String("+proj=utm +zone=40 +south +ellps=GRS80 +units=m +no_defs ").orElse(null);
        RRAF1991UTMZone20N = ProjectionInfo.fromProj4String("+proj=utm +zone=20 +ellps=WGS84 +units=m +no_defs ").orElse(null);
        SainteAnneUTMZone20N = ProjectionInfo.fromProj4String("+proj=utm +zone=20 +ellps=intl +units=m +no_defs ").orElse(null);
        SaintPierreetMiquelon1950UTMZone21N = ProjectionInfo.fromProj4String("+proj=utm +zone=21 +ellps=clrk66 +units=m +no_defs ").orElse(null);
        SambojaUTMZone50S = ProjectionInfo.fromProj4String("+proj=utm +zone=50 +south +ellps=bessel +units=m +no_defs ").orElse(null);
        SaoBrazUTMZone26N = ProjectionInfo.fromProj4String("+proj=utm +zone=26 +ellps=intl +units=m +no_defs ").orElse(null);
        SapperHill1943UTMZone20S = ProjectionInfo.fromProj4String("+proj=utm +zone=20 +south +ellps=intl +units=m +no_defs ").orElse(null);
        SapperHill1943UTMZone21S = ProjectionInfo.fromProj4String("+proj=utm +zone=21 +south +ellps=intl +units=m +no_defs ").orElse(null);
        SchwarzeckUTMZone33S = ProjectionInfo.fromProj4String("+proj=utm +zone=33 +south +ellps=bess_nam +units=m +no_defs ").orElse(null);
        SelvagemGrande1938UTMZone28N = ProjectionInfo.fromProj4String("+proj=utm +zone=28 +ellps=intl +units=m +no_defs ").orElse(null);
        SierraLeone1968UTMZone28N = ProjectionInfo.fromProj4String("+proj=utm +zone=28 +ellps=clrk80 +units=m +no_defs ").orElse(null);
        SierraLeone1968UTMZone29N = ProjectionInfo.fromProj4String("+proj=utm +zone=29 +ellps=clrk80 +units=m +no_defs ").orElse(null);
        SIRGASUTMZone17N = ProjectionInfo.fromProj4String("+proj=utm +zone=17 +ellps=GRS80 +units=m +no_defs ").orElse(null);
        SIRGASUTMZone17S = ProjectionInfo.fromProj4String("+proj=utm +zone=17 +south +ellps=GRS80 +units=m +no_defs ").orElse(null);
        SIRGASUTMZone18N = ProjectionInfo.fromProj4String("+proj=utm +zone=18 +ellps=GRS80 +units=m +no_defs ").orElse(null);
        SIRGASUTMZone18S = ProjectionInfo.fromProj4String("+proj=utm +zone=18 +south +ellps=GRS80 +units=m +no_defs ").orElse(null);
        SIRGASUTMZone19N = ProjectionInfo.fromProj4String("+proj=utm +zone=19 +ellps=GRS80 +units=m +no_defs ").orElse(null);
        SIRGASUTMZone19S = ProjectionInfo.fromProj4String("+proj=utm +zone=19 +south +ellps=GRS80 +units=m +no_defs ").orElse(null);
        SIRGASUTMZone20N = ProjectionInfo.fromProj4String("+proj=utm +zone=20 +ellps=GRS80 +units=m +no_defs ").orElse(null);
        SIRGASUTMZone20S = ProjectionInfo.fromProj4String("+proj=utm +zone=20 +south +ellps=GRS80 +units=m +no_defs ").orElse(null);
        SIRGASUTMZone21N = ProjectionInfo.fromProj4String("+proj=utm +zone=21 +ellps=GRS80 +units=m +no_defs ").orElse(null);
        SIRGASUTMZone21S = ProjectionInfo.fromProj4String("+proj=utm +zone=21 +south +ellps=GRS80 +units=m +no_defs ").orElse(null);
        SIRGASUTMZone22N = ProjectionInfo.fromProj4String("+proj=utm +zone=22 +ellps=GRS80 +units=m +no_defs ").orElse(null);
        SIRGASUTMZone22S = ProjectionInfo.fromProj4String("+proj=utm +zone=22 +south +ellps=GRS80 +units=m +no_defs ").orElse(null);
        SIRGASUTMZone23S = ProjectionInfo.fromProj4String("+proj=utm +zone=23 +south +ellps=GRS80 +units=m +no_defs ").orElse(null);
        SIRGASUTMZone24S = ProjectionInfo.fromProj4String("+proj=utm +zone=24 +south +ellps=GRS80 +units=m +no_defs ").orElse(null);
        SIRGASUTMZone25S = ProjectionInfo.fromProj4String("+proj=utm +zone=25 +south +ellps=GRS80 +units=m +no_defs ").orElse(null);
        SouthAmerican1969UTMZone17S = ProjectionInfo.fromProj4String("+proj=utm +zone=17 +south +ellps=aust_SA +units=m +no_defs ").orElse(null);
        SouthAmerican1969UTMZone18N = ProjectionInfo.fromProj4String("+proj=utm +zone=18 +ellps=aust_SA +units=m +no_defs ").orElse(null);
        SouthAmerican1969UTMZone18S = ProjectionInfo.fromProj4String("+proj=utm +zone=18 +south +ellps=aust_SA +units=m +no_defs ").orElse(null);
        SouthAmerican1969UTMZone19N = ProjectionInfo.fromProj4String("+proj=utm +zone=19 +ellps=aust_SA +units=m +no_defs ").orElse(null);
        SouthAmerican1969UTMZone19S = ProjectionInfo.fromProj4String("+proj=utm +zone=19 +south +ellps=aust_SA +units=m +no_defs ").orElse(null);
        SouthAmerican1969UTMZone20N = ProjectionInfo.fromProj4String("+proj=utm +zone=20 +ellps=aust_SA +units=m +no_defs ").orElse(null);
        SouthAmerican1969UTMZone20S = ProjectionInfo.fromProj4String("+proj=utm +zone=20 +south +ellps=aust_SA +units=m +no_defs ").orElse(null);
        SouthAmerican1969UTMZone21N = ProjectionInfo.fromProj4String("+proj=utm +zone=21 +ellps=aust_SA +units=m +no_defs ").orElse(null);
        SouthAmerican1969UTMZone21S = ProjectionInfo.fromProj4String("+proj=utm +zone=21 +south +ellps=aust_SA +units=m +no_defs ").orElse(null);
        SouthAmerican1969UTMZone22N = ProjectionInfo.fromProj4String("+proj=utm +zone=22 +ellps=aust_SA +units=m +no_defs ").orElse(null);
        SouthAmerican1969UTMZone22S = ProjectionInfo.fromProj4String("+proj=utm +zone=22 +south +ellps=aust_SA +units=m +no_defs ").orElse(null);
        SouthAmerican1969UTMZone23S = ProjectionInfo.fromProj4String("+proj=utm +zone=23 +south +ellps=aust_SA +units=m +no_defs ").orElse(null);
        SouthAmerican1969UTMZone24S = ProjectionInfo.fromProj4String("+proj=utm +zone=24 +south +ellps=aust_SA +units=m +no_defs ").orElse(null);
        SouthAmerican1969UTMZone25S = ProjectionInfo.fromProj4String("+proj=utm +zone=25 +south +ellps=aust_SA +units=m +no_defs ").orElse(null);
        ST71BelepUTMZone58S = ProjectionInfo.fromProj4String("+proj=utm +zone=58 +south +ellps=intl +units=m +no_defs ").orElse(null);
        ST84IledesPinsUTMZone58S = ProjectionInfo.fromProj4String("+proj=utm +zone=58 +south +ellps=intl +units=m +no_defs ").orElse(null);
        ST87OuveaUTMZone58S = ProjectionInfo.fromProj4String("+proj=utm +zone=58 +south +ellps=intl +units=m +no_defs ").orElse(null);
        SudanUTMZone35N = ProjectionInfo.fromProj4String("+proj=utm +zone=35 +a=6378249.2 +b=6356514.999904194 +units=m +no_defs ").orElse(null);
        SudanUTMZone36N = ProjectionInfo.fromProj4String("+proj=utm +zone=36 +a=6378249.2 +b=6356514.999904194 +units=m +no_defs ").orElse(null);
        TahaaUTMZone5S = ProjectionInfo.fromProj4String("+proj=utm +zone=5 +south +ellps=intl +units=m +no_defs ").orElse(null);
        TahitiUTMZone6S = ProjectionInfo.fromProj4String("+proj=utm +zone=6 +south +ellps=intl +units=m +no_defs ").orElse(null);
        Tananarive1925UTMZone38S = ProjectionInfo.fromProj4String("+proj=utm +zone=38 +south +ellps=intl +units=m +no_defs ").orElse(null);
        Tananarive1925UTMZone39S = ProjectionInfo.fromProj4String("+proj=utm +zone=39 +south +ellps=intl +units=m +no_defs ").orElse(null);
        TeteUTMZone36S = ProjectionInfo.fromProj4String("+proj=utm +zone=36 +south +ellps=clrk66 +units=m +no_defs ").orElse(null);
        TeteUTMZone37S = ProjectionInfo.fromProj4String("+proj=utm +zone=37 +south +ellps=clrk66 +units=m +no_defs ").orElse(null);
        Timbalai1948UTMZone49N = ProjectionInfo.fromProj4String("+proj=utm +zone=49 +ellps=evrstSS +units=m +no_defs ").orElse(null);
        Timbalai1948UTMZone50N = ProjectionInfo.fromProj4String("+proj=utm +zone=50 +ellps=evrstSS +units=m +no_defs ").orElse(null);
        TokyoUTMZone51N = ProjectionInfo.fromProj4String("+proj=utm +zone=51 +ellps=bessel +units=m +no_defs ").orElse(null);
        TokyoUTMZone52N = ProjectionInfo.fromProj4String("+proj=utm +zone=52 +ellps=bessel +units=m +no_defs ").orElse(null);
        TokyoUTMZone53N = ProjectionInfo.fromProj4String("+proj=utm +zone=53 +ellps=bessel +units=m +no_defs ").orElse(null);
        TokyoUTMZone54N = ProjectionInfo.fromProj4String("+proj=utm +zone=54 +ellps=bessel +units=m +no_defs ").orElse(null);
        TokyoUTMZone55N = ProjectionInfo.fromProj4String("+proj=utm +zone=55 +ellps=bessel +units=m +no_defs ").orElse(null);
        TokyoUTMZone56N = ProjectionInfo.fromProj4String("+proj=utm +zone=56 +ellps=bessel +units=m +no_defs ").orElse(null);
        TrucialCoast1948UTMZone39N = ProjectionInfo.fromProj4String("+proj=utm +zone=39 +ellps=helmert +units=m +no_defs ").orElse(null);
        TrucialCoast1948UTMZone40N = ProjectionInfo.fromProj4String("+proj=utm +zone=40 +ellps=helmert +units=m +no_defs ").orElse(null);
        YemenNGN1996UTMZone38N = ProjectionInfo.fromProj4String("+proj=utm +zone=38 +ellps=WGS84 +units=m +no_defs ").orElse(null);
        YemenNGN1996UTMZone39N = ProjectionInfo.fromProj4String("+proj=utm +zone=39 +ellps=WGS84 +units=m +no_defs ").orElse(null);
        Yoff1972UTMZone28N = ProjectionInfo.fromProj4String("+proj=utm +zone=28 +a=6378249.2 +b=6356514.999904194 +units=m +no_defs ").orElse(null);
        Zanderij1972UTMZone21N = ProjectionInfo.fromProj4String("+proj=utm +zone=21 +ellps=intl +units=m +no_defs ").orElse(null);

        Abidjan1987UTMZone29N.setName("Abidjan_1987_UTM_Zone_29N");
        Abidjan1987UTMZone30N.setName("Abidjan_1987_UTM_Zone_30N");
        AdindanUTMZone37N.setName("Adindan_UTM_Zone_37N");
        AdindanUTMZone38N.setName("Adindan_UTM_Zone_38N");
        AfgooyeUTMZone38N.setName("Afgooye_UTM_Zone_38N");
        AfgooyeUTMZone39N.setName("Afgooye_UTM_Zone_39N");
        AinelAbd1970UTMZone37N.setName("Ain_el_Abd_UTM_Zone_37N");
        AinelAbd1970UTMZone38N.setName("Ain_el_Abd_UTM_Zone_38N");
        AinelAbd1970UTMZone39N.setName("Ain_el_Abd_UTM_Zone_39N");
        AmericanSamoa1962UTMZone2S.setName("American_Samoa_1962_UTM_Zone_2S");
        AratuUTMZone22S.setName("Aratu_UTM_Zone_22S");
        AratuUTMZone23S.setName("Aratu_UTM_Zone_23S");
        AratuUTMZone24S.setName("Aratu_UTM_Zone_24S");
        Arc1950UTMZone34S.setName("Arc_1950_UTM_Zone_34S");
        Arc1950UTMZone35S.setName("Arc_1950_UTM_Zone_35S");
        Arc1950UTMZone36S.setName("Arc_1950_UTM_Zone_36S");
        Arc1960UTMZone35N.setName("Arc_1960_UTM_Zone_35N");
        Arc1960UTMZone35S.setName("Arc_1960_UTM_Zone_35S");
        Arc1960UTMZone36N.setName("Arc_1960_UTM_Zone_36N");
        Arc1960UTMZone36S.setName("Arc_1960_UTM_Zone_36S");
        Arc1960UTMZone37N.setName("Arc_1960_UTM_Zone_37N");
        Arc1960UTMZone37S.setName("Arc_1960_UTM_Zone_37S");
        ATS1977UTMZone19N.setName("ATS_1977_UTM_Zone_19N");
        ATS1977UTMZone20N.setName("ATS_1977_UTM_Zone_20N");
        AzoresCentral1995UTMZone26N.setName("Azores_Central_1995_UTM_Zone_26N");
        AzoresOriental1995UTMZone26N.setName("Azores_Oriental_1995_UTM_Zone_26N");
        BataviaUTMZone48S.setName("Batavia_UTM_Zone_48S");
        BataviaUTMZone49S.setName("Batavia_UTM_Zone_49S");
        BataviaUTMZone50S.setName("Batavia_UTM_Zone_50S");
        BissauUTMZone28N.setName("Bissau_UTM_Zone_28N");
        BogotaUTMZone17N.setName("Bogota_UTM_Zone_17N");
        BogotaUTMZone18N.setName("Bogota_UTM_Zone_18N");
        CamacupaUTMZone32S.setName("Camacupa_UTM_Zone_32S");
        CamacupaUTMZone33S.setName("Camacupa_UTM_Zone_33S");
        CampoInchauspeUTM19S.setName("Campo_Inchauspe_UTM_19S");
        CampoInchauspeUTM20S.setName("Campo_Inchauspe_UTM_20S");
        CapeUTMZone34S.setName("Cape_UTM_Zone_34S");
        CapeUTMZone35S.setName("Cape_UTM_Zone_35S");
        CapeUTMZone36S.setName("Cape_UTM_Zone_36S");
        CarthageUTMZone32N.setName("Carthage_UTM_Zone_32N");
        Combani1950UTMZone38S.setName("Combani_1950_UTM_38S");
        Conakry1905UTMZone28N.setName("Conakry_1905_UTM_Zone_28N");
        Conakry1905UTMZone29N.setName("Conakry_1905_UTM_Zone_29N");
        CorregoAlegreUTMZone23S.setName("Corrego_Alegre_UTM_Zone_23S");
        CorregoAlegreUTMZone24S.setName("Corrego_Alegre_UTM_Zone_24S");
        CSG1967UTMZone22N.setName("CSG_1967_UTM_22N");
        DabolaUTMZone28N.setName("Dabola_UTM_Zone_28N");
        DabolaUTMZone29N.setName("Dabola_UTM_Zone_29N");
        Datum73UTMZone29N.setName("Datum_73_UTM_Zone_29N");
        DoualaUTMZone32N.setName("Douala_UTM_Zone_32N");
        ED1950ED77UTMZone38N.setName("ED_1950_ED77_UTM_Zone_38N");
        ED1950ED77UTMZone39N.setName("ED_1950_ED77_UTM_Zone_39N");
        ED1950ED77UTMZone40N.setName("ED_1950_ED77_UTM_Zone_40N");
        ED1950ED77UTMZone41N.setName("ED_1950_ED77_UTM_Zone_41N");
        ELD1979UTMZone32N.setName("ELD_1979_UTM_Zone_32N");
        ELD1979UTMZone33N.setName("ELD_1979_UTM_Zone_33N");
        ELD1979UTMZone34N.setName("ELD_1979_UTM_Zone_34N");
        ELD1979UTMZone35N.setName("ELD_1979_UTM_Zone_35N");
        ETRF1989UTMZone28N.setName("ETRF_1989_UTM_Zone_28N");
        ETRF1989UTMZone29N.setName("ETRF_1989_UTM_Zone_29N");
        ETRF1989UTMZone30N.setName("ETRF_1989_UTM_Zone_30N");
        ETRF1989UTMZone31N.setName("ETRF_1989_UTM_Zone_31N");
        ETRF1989UTMZone32N.setName("ETRF_1989_UTM_Zone_32N");
        ETRF1989UTMZone33N.setName("ETRF_1989_UTM_Zone_33N");
        ETRF1989UTMZone34N.setName("ETRF_1989_UTM_Zone_34N");
        ETRF1989UTMZone35N.setName("ETRF_1989_UTM_Zone_35N");
        ETRF1989UTMZone36N.setName("ETRF_1989_UTM_Zone_36N");
        ETRF1989UTMZone37N.setName("ETRF_1989_UTM_Zone_37N");
        ETRF1989UTMZone38N.setName("ETRF_1989_UTM_Zone_38N");
        ETRS1989UTMZone26N.setName("ETRS_1989_UTM_Zone_26N");
        ETRS1989UTMZone27N.setName("ETRS_1989_UTM_Zone_27N");
        ETRS1989UTMZone28N.setName("ETRS_1989_UTM_Zone_28N");
        ETRS1989UTMZone29N.setName("ETRS_1989_UTM_Zone_29N");
        ETRS1989UTMZone30N.setName("ETRS_1989_UTM_Zone_30N");
        ETRS1989UTMZone31N.setName("ETRS_1989_UTM_Zone_31N");
        ETRS1989UTMZone32N.setName("ETRS_1989_UTM_Zone_32N");
        ETRS1989UTMZone33N.setName("ETRS_1989_UTM_Zone_33N");
        ETRS1989UTMZone34N.setName("ETRS_1989_UTM_Zone_34N");
        ETRS1989UTMZone35N.setName("ETRS_1989_UTM_Zone_35N");
        ETRS1989UTMZone36N.setName("ETRS_1989_UTM_Zone_36N");
        ETRS1989UTMZone37N.setName("ETRS_1989_UTM_Zone_37N");
        ETRS1989UTMZone38N.setName("ETRS_1989_UTM_Zone_38N");
        ETRS1989UTMZone39N.setName("ETRS_1989_UTM_Zone_39N");
        EuropeanDatum1950UTMZone28N.setName("ED_1950_UTM_Zone_28N");
        EuropeanDatum1950UTMZone29N.setName("ED_1950_UTM_Zone_29N");
        EuropeanDatum1950UTMZone30N.setName("ED_1950_UTM_Zone_30N");
        EuropeanDatum1950UTMZone31N.setName("ED_1950_UTM_Zone_31N");
        EuropeanDatum1950UTMZone32N.setName("ED_1950_UTM_Zone_32N");
        EuropeanDatum1950UTMZone33N.setName("ED_1950_UTM_Zone_33N");
        EuropeanDatum1950UTMZone34N.setName("ED_1950_UTM_Zone_34N");
        EuropeanDatum1950UTMZone35N.setName("ED_1950_UTM_Zone_35N");
        EuropeanDatum1950UTMZone36N.setName("ED_1950_UTM_Zone_36N");
        EuropeanDatum1950UTMZone37N.setName("ED_1950_UTM_Zone_37N");
        EuropeanDatum1950UTMZone38N.setName("ED_1950_UTM_Zone_38N");
        FahudUTMZone39N.setName("Fahud_UTM_Zone_39N");
        FahudUTMZone40N.setName("Fahud_UTM_Zone_40N");
        FortDesaixUTMZone20N.setName("Fort_Desaix_UTM_20N");
        FortMarigotUTMZone20N.setName("Fort_Marigot_UTM_20N");
        GarouaUTMZone33N.setName("Garoua_UTM_Zone_33N");
        GraciosaBaseSW1948UTMZone26N.setName("Graciosa_Base_SW_1948_UTM_Zone_26N");
        GrandComorosUTMZone38S.setName("Grand_Comoros_UTM_38S");
        HitoXVIII1963UTMZone19S.setName("Hito_XVIII_1963_UTM_19S");
        Hjorsey1955UTMZone26N.setName("Hjorsey_1955_UTM_Zone_26N");
        Hjorsey1955UTMZone27N.setName("Hjorsey_1955_UTM_Zone_27N");
        Hjorsey1955UTMZone28N.setName("Hjorsey_1955_UTM_Zone_28N");
        HongKong1980UTMZone49N.setName("Hong_Kong_1980_UTM_Zone_49N");
        HongKong1980UTMZone50N.setName("Hong_Kong_1980_UTM_Zone_50N");
        IGM1995UTMZone32N.setName("IGM_1995_UTM_Zone_32N");
        IGM1995UTMZone33N.setName("IGM_1995_UTM_Zone_33N");
        IGN53MareUTMZone58S.setName("IGN53_Mare_UTM_58S");
        IGN56LifouUTMZone58S.setName("IGN56_Lifou_UTM_58S");
        IGN72GrandeTerreUTMZone58S.setName("IGN72_Grande_Terre_UTM_58S");
        IGN72NukuHivaUTMZone7S.setName("IGN72_Nuku_Hiva_UTM_7S");
        Indian1954UTMZone46N.setName("Indian_1954_UTM_Zone_46N");
        Indian1954UTMZone47N.setName("Indian_1954_UTM_Zone_47N");
        Indian1954UTMZone48N.setName("Indian_1954_UTM_Zone_48N");
        Indian1960UTMZone48N.setName("Indian_1960_UTM_Zone_48N");
        Indian1960UTMZone49N.setName("Indian_1960_UTM_Zone_49N");
        Indian1975UTMZone47N.setName("Indian_1975_UTM_Zone_47N");
        Indian1975UTMZone48N.setName("Indian_1975_UTM_Zone_48N");
        Indonesia1974UTMZone46N.setName("Indonesian_1974_UTM_Zone_46N");
        Indonesia1974UTMZone46S.setName("Indonesian_1974_UTM_Zone_46S");
        Indonesia1974UTMZone47N.setName("Indonesian_1974_UTM_Zone_47N");
        Indonesia1974UTMZone47S.setName("Indonesian_1974_UTM_Zone_47S");
        Indonesia1974UTMZone48N.setName("Indonesian_1974_UTM_Zone_48N");
        Indonesia1974UTMZone48S.setName("Indonesian_1974_UTM_Zone_48S");
        Indonesia1974UTMZone49N.setName("Indonesian_1974_UTM_Zone_49N");
        Indonesia1974UTMZone49S.setName("Indonesian_1974_UTM_Zone_49S");
        Indonesia1974UTMZone50N.setName("Indonesian_1974_UTM_Zone_50N");
        Indonesia1974UTMZone50S.setName("Indonesian_1974_UTM_Zone_50S");
        Indonesia1974UTMZone51N.setName("Indonesian_1974_UTM_Zone_51N");
        Indonesia1974UTMZone51S.setName("Indonesian_1974_UTM_Zone_51S");
        Indonesia1974UTMZone52N.setName("Indonesian_1974_UTM_Zone_52N");
        Indonesia1974UTMZone52S.setName("Indonesian_1974_UTM_Zone_52S");
        Indonesia1974UTMZone53N.setName("Indonesian_1974_UTM_Zone_53N");
        Indonesia1974UTMZone53S.setName("Indonesian_1974_UTM_Zone_53S");
        Indonesia1974UTMZone54S.setName("Indonesian_1974_UTM_Zone_54S");
        IRENET95UTMZone29N.setName("IRENET95_UTM_Zone_29N");
        JGD2000UTMZone51N.setName("JGD_2000_UTM_Zone_51N");
        JGD2000UTMZone52N.setName("JGD_2000_UTM_Zone_52N");
        JGD2000UTMZone53N.setName("JGD_2000_UTM_Zone_53N");
        JGD2000UTMZone54N.setName("JGD_2000_UTM_Zone_54N");
        JGD2000UTMZone55N.setName("JGD_2000_UTM_Zone_55N");
        JGD2000UTMZone56N.setName("JGD_2000_UTM_Zone_56N");
        K01949UTMZone42S.setName("K0_1949_UTM_42S");
        KertauUTMZone47N.setName("Kertau_UTM_Zone_47N");
        KertauUTMZone48N.setName("Kertau_UTM_Zone_48N");
        KousseriUTMZone33N.setName("Kousseri_UTM_Zone_33N");
        LaCanoaUTMZone18N.setName("La_Canoa_UTM_Zone_18N");
        LaCanoaUTMZone19N.setName("La_Canoa_UTM_Zone_19N");
        LaCanoaUTMZone20N.setName("La_Canoa_UTM_Zone_20N");
        LaCanoaUTMZone21N.setName("La_Canoa_UTM_Zone_21N");
        Locodjo1965UTMZone29N.setName("Locodjo_1965_UTM_Zone_29N");
        Locodjo1965UTMZone30N.setName("Locodjo_1965_UTM_Zone_30N");
        LomeUTMZone31N.setName("Lome_UTM_Zone_31N");
        Malongo1987UTMZone32S.setName("Mporaloko_UTM_Zone_32S");
        Manoca1962UTMZone32N.setName("Mporaloko_UTM_Zone_32N");
        MassawaUTMZone37N.setName("Massawa_UTM_Zone_37N");
        MhastUTMZone32S.setName("Mhast_Offshore_UTM_Zone_32S");
        MinnaUTMZone31N.setName("Minna_UTM_Zone_31N");
        MinnaUTMZone32N.setName("Minna_UTM_Zone_32N");
        MOP78UTMZone1S.setName("MOP78_UTM_1S");
        MoznetUTMZone36S.setName("Moznet_UTM_Zone_36S");
        MoznetUTMZone37S.setName("Moznet_UTM_Zone_37S");
        NAD1927BLMZone14N.setName("NAD_1927_BLM_Zone_14N");
        NAD1927BLMZone15N.setName("NAD_1927_BLM_Zone_15N");
        NAD1927BLMZone16N.setName("NAD_1927_BLM_Zone_16N");
        NAD1927BLMZone17N.setName("NAD_1927_BLM_Zone_17N");
        NAD1983HARNUTMZone11N.setName("NAD_1983_HARN_UTM_Zone_11N");
        NAD1983HARNUTMZone12N.setName("NAD_1983_HARN_UTM_Zone_12N");
        NAD1983HARNUTMZone13N.setName("NAD_1983_HARN_UTM_Zone_13N");
        NAD1983HARNUTMZone18N.setName("NAD_1983_HARN_UTM_Zone_18N");
        NAD1983HARNUTMZone2S.setName("NAD_1983_HARN_UTM_Zone_2S");
        NAD1983HARNUTMZone4N.setName("NAD_1983_HARN_UTM_Zone_4N");
        NAD1983HARNUTMZone5N.setName("NAD_1983_HARN_UTM_Zone_5N");
        Nahrwan1967UTMZone38N.setName("Nahrwan_1967_UTM_Zone_38N");
        Nahrwan1967UTMZone39N.setName("Nahrwan_1967_UTM_Zone_39N");
        Nahrwan1967UTMZone40N.setName("Nahrwan_1967_UTM_Zone_40N");
        Naparima1955UTMZone20N.setName("Naparima_1955_UTM_Zone_20N");
        Naparima1972UTMZone20N.setName("Naparima_1972_UTM_Zone_20N");
        NEA74NoumeaUTMZone58S.setName("NEA74_Noumea_UTM_58S");
        NGNUTMZone38N.setName("NGN_UTM_Zone_38N");
        NGNUTMZone39N.setName("NGN_UTM_Zone_39N");
        NGO1948UTMZone32N.setName("NGO_1948_UTM_Zone_32N");
        NGO1948UTMZone33N.setName("NGO_1948_UTM_Zone_33N");
        NGO1948UTMZone34N.setName("NGO_1948_UTM_Zone_34N");
        NGO1948UTMZone35N.setName("NGO_1948_UTM_Zone_35N");
        NordSahara1959UTMZone29N.setName("Nord_Sahara_1959_UTM_Zone_29N");
        NordSahara1959UTMZone30N.setName("Nord_Sahara_1959_UTM_Zone_30N");
        NordSahara1959UTMZone31N.setName("Nord_Sahara_1959_UTM_Zone_31N");
        NordSahara1959UTMZone32N.setName("Nord_Sahara_1959_UTM_Zone_32N");
        NZGD1949UTMZone58S.setName("NZGD_1949_UTM_Zone_58S");
        NZGD1949UTMZone59S.setName("NZGD_1949_UTM_Zone_59S");
        NZGD1949UTMZone60S.setName("NZGD_1949_UTM_Zone_60S");
        NZGD2000UTMZone58S.setName("NZGD_2000_UTM_Zone_58S");
        NZGD2000UTMZone59S.setName("NZGD_2000_UTM_Zone_59S");
        NZGD2000UTMZone60S.setName("NZGD_2000_UTM_Zone_60S");
        ObservMeteorologico1939UTMZone25N.setName("Observ_Meteorologico_1939_UTM_Zone_25N");
        OldHawaiianUTMZone4N.setName("Old_Hawaiian_UTM_Zone_4N");
        OldHawaiianUTMZone5N.setName("Old_Hawaiian_UTM_Zone_5N");
        PDO1993UTMZone39N.setName("PDO_1993_UTM_Zone_39N");
        PDO1993UTMZone40N.setName("PDO_1993_UTM_Zone_40N");
        PointeNoireUTMZone32S.setName("Pointe_Noire_UTM_Zone_32S");
        PortoSanto1936UTMZone28N.setName("Porto_Santo_1936_UTM_Zone_28N");
        PortoSanto1995UTMZone28N.setName("Porto_Santo_1995_UTM_Zone_28N");
        ProvSAmerDatumUTMZone17s.setName("PSAD_1956_UTM_Zone_17S");
        ProvSAmerDatumUTMZone18N.setName("PSAD_1956_UTM_Zone_18N");
        ProvSAmerDatumUTMZone18S.setName("PSAD_1956_UTM_Zone_18S");
        ProvSAmerDatumUTMZone19N.setName("PSAD_1956_UTM_Zone_19N");
        ProvSAmerDatumUTMZone19S.setName("PSAD_1956_UTM_Zone_19S");
        ProvSAmerDatumUTMZone20N.setName("PSAD_1956_UTM_Zone_20N");
        ProvSAmerDatumUTMZone20S.setName("PSAD_1956_UTM_Zone_20S");
        ProvSAmerDatumUTMZone21N.setName("PSAD_1956_UTM_Zone_21N");
        ProvSAmerDatumUTMZone22S.setName("PSAD_1956_UTM_Zone_22S");
        PuertoRicoUTMZone20N.setName("Puerto_Rico_UTM_Zone_20N");
        Qornoq1927UTMZone22N.setName("Qornoq_1927_UTM_Zone_22");
        Qornoq1927UTMZone23N.setName("Qornoq_1927_UTM_Zone_23");
        REGVENUTMZone18N.setName("REGVEN_UTM_Zone_18");
        REGVENUTMZone19N.setName("REGVEN_UTM_Zone_19");
        REGVENUTMZone20N.setName("REGVEN_UTM_Zone_20");
        RGFG1995UTMZone22N.setName("RGFG_1995_UTM_22N");
        RGR1992UTMZone40S.setName("RGR_1992_UTM_40S");
        RRAF1991UTMZone20N.setName("RRAF_1991_UTM_20N");
        SainteAnneUTMZone20N.setName("Sainte_Anne_UTM_20N");
        SambojaUTMZone50S.setName("Samboja_UTM_Zone_50S");
        SaoBrazUTMZone26N.setName("Sao_Braz_UTM_Zone_26N");
        SapperHill1943UTMZone20S.setName("Sapper_Hill_1943_UTM_Zone_20S");
        SapperHill1943UTMZone21S.setName("Sapper_Hill_1943_UTM_Zone_21S");
        SchwarzeckUTMZone33S.setName("Schwarzeck_UTM_Zone_33S");
        SelvagemGrande1938UTMZone28N.setName("Selvagem_Grande_1938_UTM_Zone_28N");
        SierraLeone1968UTMZone28N.setName("Sierra_Leone_1968_UTM_Zone_28N");
        SierraLeone1968UTMZone29N.setName("Sierra_Leone_1968_UTM_Zone_29N");
        SIRGASUTMZone17N.setName("SIRGAS_2000_UTM_Zone_17N");
        SIRGASUTMZone17S.setName("SIRGAS_2000_UTM_Zone_17S");
        SIRGASUTMZone18N.setName("SIRGAS_2000_UTM_Zone_18N");
        SIRGASUTMZone18S.setName("SIRGAS_2000_UTM_Zone_18S");
        SIRGASUTMZone19N.setName("SIRGAS_2000_UTM_Zone_19N");
        SIRGASUTMZone19S.setName("SIRGAS_2000_UTM_Zone_19S");
        SIRGASUTMZone20N.setName("SIRGAS_2000_UTM_Zone_20N");
        SIRGASUTMZone20S.setName("SIRGAS_2000_UTM_Zone_20S");
        SIRGASUTMZone21N.setName("SIRGAS_2000_UTM_Zone_21N");
        SIRGASUTMZone21S.setName("SIRGAS_2000_UTM_Zone_21S");
        SIRGASUTMZone22N.setName("SIRGAS_2000_UTM_Zone_22N");
        SIRGASUTMZone22S.setName("SIRGAS_2000_UTM_Zone_22S");
        SIRGASUTMZone23S.setName("SIRGAS_2000_UTM_Zone_23S");
        SIRGASUTMZone24S.setName("SIRGAS_2000_UTM_Zone_24S");
        SIRGASUTMZone25S.setName("SIRGAS_2000_UTM_Zone_25S");
        SouthAmerican1969UTMZone17S.setName("SIRGAS_UTM_Zone_17S");
        SouthAmerican1969UTMZone18N.setName("SIRGAS_UTM_Zone_18N");
        SouthAmerican1969UTMZone18S.setName("SIRGAS_UTM_Zone_18S");
        SouthAmerican1969UTMZone19N.setName("SIRGAS_UTM_Zone_19N");
        SouthAmerican1969UTMZone19S.setName("SIRGAS_UTM_Zone_19S");
        SouthAmerican1969UTMZone20N.setName("SIRGAS_UTM_Zone_20N");
        SouthAmerican1969UTMZone20S.setName("SIRGAS_UTM_Zone_20S");
        SouthAmerican1969UTMZone21N.setName("SIRGAS_UTM_Zone_21N");
        SouthAmerican1969UTMZone21S.setName("SIRGAS_UTM_Zone_21S");
        SouthAmerican1969UTMZone22N.setName("SIRGAS_UTM_Zone_22N");
        SouthAmerican1969UTMZone22S.setName("SIRGAS_UTM_Zone_22S");
        SouthAmerican1969UTMZone23S.setName("SIRGAS_UTM_Zone_23S");
        SouthAmerican1969UTMZone24S.setName("SIRGAS_UTM_Zone_24S");
        SouthAmerican1969UTMZone25S.setName("SIRGAS_UTM_Zone_25S");
        ST71BelepUTMZone58S.setName("ST71_Belep_UTM_58S");
        ST84IledesPinsUTMZone58S.setName("ST84_Ile_des_Pins_UTM_58S");
        ST87OuveaUTMZone58S.setName("ST87_Ouvea_UTM_58S");
        SudanUTMZone35N.setName("Sudan_UTM_Zone_35N");
        SudanUTMZone36N.setName("Sudan_UTM_Zone_36N");
        TahaaUTMZone5S.setName("Tahaa_UTM_5S");
        TahitiUTMZone6S.setName("Tahiti_1979_UTM_Zone_6S");
        Tananarive1925UTMZone38S.setName("Tananarive_1925_UTM_Zone_38S");
        Tananarive1925UTMZone39S.setName("Tananarive_1925_UTM_Zone_39S");
        TeteUTMZone36S.setName("Tete_UTM_Zone_36S");
        TeteUTMZone37S.setName("Tete_UTM_Zone_37S");
        Timbalai1948UTMZone49N.setName("Timbalai_1948_UTM_Zone_49N");
        Timbalai1948UTMZone50N.setName("Timbalai_1948_UTM_Zone_50N");
        TokyoUTMZone51N.setName("Tokyo_UTM_Zone_51N");
        TokyoUTMZone52N.setName("Tokyo_UTM_Zone_52N");
        TokyoUTMZone53N.setName("Tokyo_UTM_Zone_53N");
        TokyoUTMZone54N.setName("Tokyo_UTM_Zone_54N");
        TokyoUTMZone55N.setName("Tokyo_UTM_Zone_55N");
        TokyoUTMZone56N.setName("Tokyo_UTM_Zone_56N");
        TrucialCoast1948UTMZone39N.setName("TC_1948_UTM_Zone_39N");
        TrucialCoast1948UTMZone40N.setName("TC_1948_UTM_Zone_40N");
        YemenNGN1996UTMZone38N.setName("Yemen_NGN_1996_UTM_Zone_38N");
        YemenNGN1996UTMZone39N.setName("Yemen_NGN_1996_UTM_Zone_39N");
        Yoff1972UTMZone28N.setName("Yoff_1972_UTM_Zone_28N");
        Zanderij1972UTMZone21N.setName("Zanderij_1972_UTM_Zone_21N");

        Abidjan1987UTMZone29N.getGeographicInfo().setName("GCS_Abidjan_1987");
        Abidjan1987UTMZone30N.getGeographicInfo().setName("GCS_Abidjan_1987");
        AdindanUTMZone37N.getGeographicInfo().setName("GCS_Adindan");
        AdindanUTMZone38N.getGeographicInfo().setName("GCS_Adindan");
        AfgooyeUTMZone38N.getGeographicInfo().setName("GCS_Afgooye");
        AfgooyeUTMZone39N.getGeographicInfo().setName("GCS_Afgooye");
        AinelAbd1970UTMZone37N.getGeographicInfo().setName("GCS_Ain_el_Abd_1970");
        AinelAbd1970UTMZone38N.getGeographicInfo().setName("GCS_Ain_el_Abd_1970");
        AinelAbd1970UTMZone39N.getGeographicInfo().setName("GCS_Ain_el_Abd_1970");
        AmericanSamoa1962UTMZone2S.getGeographicInfo().setName("GCS_American_Samoa_1962");
        AratuUTMZone22S.getGeographicInfo().setName("GCS_Aratu");
        AratuUTMZone23S.getGeographicInfo().setName("GCS_Aratu");
        AratuUTMZone24S.getGeographicInfo().setName("GCS_Aratu");
        Arc1950UTMZone34S.getGeographicInfo().setName("GCS_Arc_1950");
        Arc1950UTMZone35S.getGeographicInfo().setName("GCS_Arc_1950");
        Arc1950UTMZone36S.getGeographicInfo().setName("GCS_Arc_1950");
        Arc1960UTMZone35N.getGeographicInfo().setName("GCS_Arc_1960");
        Arc1960UTMZone35S.getGeographicInfo().setName("GCS_Arc_1960");
        Arc1960UTMZone36N.getGeographicInfo().setName("GCS_Arc_1960");
        Arc1960UTMZone36S.getGeographicInfo().setName("GCS_Arc_1960");
        Arc1960UTMZone37N.getGeographicInfo().setName("GCS_Arc_1960");
        Arc1960UTMZone37S.getGeographicInfo().setName("GCS_Arc_1960");
        ATS1977UTMZone19N.getGeographicInfo().setName("GCS_ATS_1977");
        ATS1977UTMZone20N.getGeographicInfo().setName("GCS_ATS_1977");
        AzoresCentral1995UTMZone26N.getGeographicInfo().setName("GCS_Azores_Central_1995");
        AzoresOriental1995UTMZone26N.getGeographicInfo().setName("GCS_Azores_Oriental_1995");
        BataviaUTMZone48S.getGeographicInfo().setName("GCS_Batavia");
        BataviaUTMZone49S.getGeographicInfo().setName("GCS_Batavia");
        BataviaUTMZone50S.getGeographicInfo().setName("GCS_Batavia");
        BissauUTMZone28N.getGeographicInfo().setName("GCS_Bissau");
        BogotaUTMZone17N.getGeographicInfo().setName("GCS_Bogota");
        BogotaUTMZone18N.getGeographicInfo().setName("GCS_Bogota");
        CamacupaUTMZone32S.getGeographicInfo().setName("GCS_Camacupa");
        CamacupaUTMZone33S.getGeographicInfo().setName("GCS_Camacupa");
        CampoInchauspeUTM19S.getGeographicInfo().setName("GCS_Campo_Inchauspe");
        CampoInchauspeUTM20S.getGeographicInfo().setName("GCS_Campo_Inchauspe");
        CapeUTMZone34S.getGeographicInfo().setName("GCS_Cape");
        CapeUTMZone35S.getGeographicInfo().setName("GCS_Cape");
        CapeUTMZone36S.getGeographicInfo().setName("GCS_Cape");
        CarthageUTMZone32N.getGeographicInfo().setName("GCS_Carthage_Degree");
        Combani1950UTMZone38S.getGeographicInfo().setName("GCS_Combani_1950");
        Conakry1905UTMZone28N.getGeographicInfo().setName("GCS_Conakry_1905");
        Conakry1905UTMZone29N.getGeographicInfo().setName("GCS_Conakry_1905");
        CorregoAlegreUTMZone23S.getGeographicInfo().setName("GCS_Corrego_Alegre");
        CorregoAlegreUTMZone24S.getGeographicInfo().setName("GCS_Corrego_Alegre");
        CSG1967UTMZone22N.getGeographicInfo().setName("GCS_CSG_1967");
        DabolaUTMZone28N.getGeographicInfo().setName("GCS_Dabola_1981");
        DabolaUTMZone29N.getGeographicInfo().setName("GCS_Dabola_1981");
        Datum73UTMZone29N.getGeographicInfo().setName("GCS_Datum_73");
        DoualaUTMZone32N.getGeographicInfo().setName("GCS_Douala");
        ED1950ED77UTMZone38N.getGeographicInfo().setName("GCS_European_1950_ED77");
        ED1950ED77UTMZone39N.getGeographicInfo().setName("GCS_European_1950_ED77");
        ED1950ED77UTMZone40N.getGeographicInfo().setName("GCS_European_1950_ED77");
        ED1950ED77UTMZone41N.getGeographicInfo().setName("GCS_European_1950_ED77");
        ELD1979UTMZone32N.getGeographicInfo().setName("GCS_European_Libyan_Datum_1979");
        ELD1979UTMZone33N.getGeographicInfo().setName("GCS_European_Libyan_Datum_1979");
        ELD1979UTMZone34N.getGeographicInfo().setName("GCS_European_Libyan_Datum_1979");
        ELD1979UTMZone35N.getGeographicInfo().setName("GCS_European_Libyan_Datum_1979");
        ETRF1989UTMZone28N.getGeographicInfo().setName("GCS_ETRF_1989");
        ETRF1989UTMZone29N.getGeographicInfo().setName("GCS_ETRF_1989");
        ETRF1989UTMZone30N.getGeographicInfo().setName("GCS_ETRF_1989");
        ETRF1989UTMZone31N.getGeographicInfo().setName("GCS_ETRF_1989");
        ETRF1989UTMZone32N.getGeographicInfo().setName("GCS_ETRF_1989");
        ETRF1989UTMZone33N.getGeographicInfo().setName("GCS_ETRF_1989");
        ETRF1989UTMZone34N.getGeographicInfo().setName("GCS_ETRF_1989");
        ETRF1989UTMZone35N.getGeographicInfo().setName("GCS_ETRF_1989");
        ETRF1989UTMZone36N.getGeographicInfo().setName("GCS_ETRF_1989");
        ETRF1989UTMZone37N.getGeographicInfo().setName("GCS_ETRF_1989");
        ETRF1989UTMZone38N.getGeographicInfo().setName("GCS_ETRF_1989");
        ETRS1989UTMZone26N.getGeographicInfo().setName("GCS_ETRS_1989");
        ETRS1989UTMZone27N.getGeographicInfo().setName("GCS_ETRS_1989");
        ETRS1989UTMZone28N.getGeographicInfo().setName("GCS_ETRS_1989");
        ETRS1989UTMZone29N.getGeographicInfo().setName("GCS_ETRS_1989");
        ETRS1989UTMZone30N.getGeographicInfo().setName("GCS_ETRS_1989");
        ETRS1989UTMZone31N.getGeographicInfo().setName("GCS_ETRS_1989");
        ETRS1989UTMZone32N.getGeographicInfo().setName("GCS_ETRS_1989");
        ETRS1989UTMZone33N.getGeographicInfo().setName("GCS_ETRS_1989");
        ETRS1989UTMZone34N.getGeographicInfo().setName("GCS_ETRS_1989");
        ETRS1989UTMZone35N.getGeographicInfo().setName("GCS_ETRS_1989");
        ETRS1989UTMZone36N.getGeographicInfo().setName("GCS_ETRS_1989");
        ETRS1989UTMZone37N.getGeographicInfo().setName("GCS_ETRS_1989");
        ETRS1989UTMZone38N.getGeographicInfo().setName("GCS_ETRS_1989");
        ETRS1989UTMZone39N.getGeographicInfo().setName("GCS_ETRS_1989");
        EuropeanDatum1950UTMZone28N.getGeographicInfo().setName("GCS_European_1950");
        EuropeanDatum1950UTMZone29N.getGeographicInfo().setName("GCS_European_1950");
        EuropeanDatum1950UTMZone30N.getGeographicInfo().setName("GCS_European_1950");
        EuropeanDatum1950UTMZone31N.getGeographicInfo().setName("GCS_European_1950");
        EuropeanDatum1950UTMZone32N.getGeographicInfo().setName("GCS_European_1950");
        EuropeanDatum1950UTMZone33N.getGeographicInfo().setName("GCS_European_1950");
        EuropeanDatum1950UTMZone34N.getGeographicInfo().setName("GCS_European_1950");
        EuropeanDatum1950UTMZone35N.getGeographicInfo().setName("GCS_European_1950");
        EuropeanDatum1950UTMZone36N.getGeographicInfo().setName("GCS_European_1950");
        EuropeanDatum1950UTMZone37N.getGeographicInfo().setName("GCS_European_1950");
        EuropeanDatum1950UTMZone38N.getGeographicInfo().setName("GCS_European_1950");
        FahudUTMZone39N.getGeographicInfo().setName("GCS_Fahud");
        FahudUTMZone40N.getGeographicInfo().setName("GCS_Fahud");
        FortDesaixUTMZone20N.getGeographicInfo().setName("GCS_Fort_Desaix");
        FortMarigotUTMZone20N.getGeographicInfo().setName("GCS_Fort_Marigot");
        GarouaUTMZone33N.getGeographicInfo().setName("GCS_Garoua");
        GraciosaBaseSW1948UTMZone26N.getGeographicInfo().setName("GCS_Graciosa_Base_SW_1948");
        GrandComorosUTMZone38S.getGeographicInfo().setName("GCS_Grand_Comoros");
        HitoXVIII1963UTMZone19S.getGeographicInfo().setName("GCS_Hito_XVIII_1963");
        Hjorsey1955UTMZone26N.getGeographicInfo().setName("GCS_Hjorsey_1955");
        Hjorsey1955UTMZone27N.getGeographicInfo().setName("GCS_Hjorsey_1955");
        Hjorsey1955UTMZone28N.getGeographicInfo().setName("GCS_Hjorsey_1955");
        HongKong1980UTMZone49N.getGeographicInfo().setName("GCS_Hong_Kong_1980");
        HongKong1980UTMZone50N.getGeographicInfo().setName("GCS_Hong_Kong_1980");
        IGM1995UTMZone32N.getGeographicInfo().setName("GCS_IGM_1995");
        IGM1995UTMZone33N.getGeographicInfo().setName("GCS_IGM_1995");
        IGN53MareUTMZone58S.getGeographicInfo().setName("GCS_IGN53_Mare");
        IGN56LifouUTMZone58S.getGeographicInfo().setName("GCS_IGN56_Lifou");
        IGN72GrandeTerreUTMZone58S.getGeographicInfo().setName("GCS_IGN72_Grande_Terre");
        IGN72NukuHivaUTMZone7S.getGeographicInfo().setName("GCS_IGN72_Nuku_Hiva");
        Indian1954UTMZone46N.getGeographicInfo().setName("GCS_Indian_1954");
        Indian1954UTMZone47N.getGeographicInfo().setName("GCS_Indian_1954");
        Indian1954UTMZone48N.getGeographicInfo().setName("GCS_Indian_1954");
        Indian1960UTMZone48N.getGeographicInfo().setName("GCS_Indian_1960");
        Indian1960UTMZone49N.getGeographicInfo().setName("GCS_Indian_1960");
        Indian1975UTMZone47N.getGeographicInfo().setName("GCS_Indian_1975");
        Indian1975UTMZone48N.getGeographicInfo().setName("GCS_Indian_1975");
        Indonesia1974UTMZone46N.getGeographicInfo().setName("GCS_Indonesian_1974");
        Indonesia1974UTMZone46S.getGeographicInfo().setName("GCS_Indonesian_1974");
        Indonesia1974UTMZone47N.getGeographicInfo().setName("GCS_Indonesian_1974");
        Indonesia1974UTMZone47S.getGeographicInfo().setName("GCS_Indonesian_1974");
        Indonesia1974UTMZone48N.getGeographicInfo().setName("GCS_Indonesian_1974");
        Indonesia1974UTMZone48S.getGeographicInfo().setName("GCS_Indonesian_1974");
        Indonesia1974UTMZone49N.getGeographicInfo().setName("GCS_Indonesian_1974");
        Indonesia1974UTMZone49S.getGeographicInfo().setName("GCS_Indonesian_1974");
        Indonesia1974UTMZone50N.getGeographicInfo().setName("GCS_Indonesian_1974");
        Indonesia1974UTMZone50S.getGeographicInfo().setName("GCS_Indonesian_1974");
        Indonesia1974UTMZone51N.getGeographicInfo().setName("GCS_Indonesian_1974");
        Indonesia1974UTMZone51S.getGeographicInfo().setName("GCS_Indonesian_1974");
        Indonesia1974UTMZone52N.getGeographicInfo().setName("GCS_Indonesian_1974");
        Indonesia1974UTMZone52S.getGeographicInfo().setName("GCS_Indonesian_1974");
        Indonesia1974UTMZone53N.getGeographicInfo().setName("GCS_Indonesian_1974");
        Indonesia1974UTMZone53S.getGeographicInfo().setName("GCS_Indonesian_1974");
        Indonesia1974UTMZone54S.getGeographicInfo().setName("GCS_Indonesian_1974");
        IRENET95UTMZone29N.getGeographicInfo().setName("GCS_IRENET95");
        JGD2000UTMZone51N.getGeographicInfo().setName("GCS_JGD_2000");
        JGD2000UTMZone52N.getGeographicInfo().setName("GCS_JGD_2000");
        JGD2000UTMZone53N.getGeographicInfo().setName("GCS_JGD_2000");
        JGD2000UTMZone54N.getGeographicInfo().setName("GCS_JGD_2000");
        JGD2000UTMZone55N.getGeographicInfo().setName("GCS_JGD_2000");
        JGD2000UTMZone56N.getGeographicInfo().setName("GCS_JGD_2000");
        K01949UTMZone42S.getGeographicInfo().setName("GCS_K0_1949");
        KertauUTMZone47N.getGeographicInfo().setName("GCS_Kertau");
        KertauUTMZone48N.getGeographicInfo().setName("GCS_Kertau");
        KousseriUTMZone33N.getGeographicInfo().setName("GCS_Kousseri");
        LaCanoaUTMZone18N.getGeographicInfo().setName("GCS_La_Canoa");
        LaCanoaUTMZone19N.getGeographicInfo().setName("GCS_La_Canoa");
        LaCanoaUTMZone20N.getGeographicInfo().setName("GCS_La_Canoa");
        LaCanoaUTMZone21N.getGeographicInfo().setName("GCS_La_Canoa");
        Locodjo1965UTMZone29N.getGeographicInfo().setName("GCS_Locodjo_1965");
        Locodjo1965UTMZone30N.getGeographicInfo().setName("GCS_Locodjo_1965");
        LomeUTMZone31N.getGeographicInfo().setName("GCS_Lome");
        Malongo1987UTMZone32S.getGeographicInfo().setName("GCS_Mporaloko");
        Manoca1962UTMZone32N.getGeographicInfo().setName("GCS_Mporaloko");
        MassawaUTMZone37N.getGeographicInfo().setName("GCS_Massawa");
        MhastUTMZone32S.getGeographicInfo().setName("GCS_Mhast_Offshore");
        MinnaUTMZone31N.getGeographicInfo().setName("GCS_Minna");
        MinnaUTMZone32N.getGeographicInfo().setName("GCS_Minna");
        MOP78UTMZone1S.getGeographicInfo().setName("GCS_MOP78");
        MoznetUTMZone36S.getGeographicInfo().setName("GCS_Moznet");
        MoznetUTMZone37S.getGeographicInfo().setName("GCS_Moznet");
        NAD1927BLMZone14N.getGeographicInfo().setName("GCS_North_American_1927");
        NAD1927BLMZone15N.getGeographicInfo().setName("GCS_North_American_1927");
        NAD1927BLMZone16N.getGeographicInfo().setName("GCS_North_American_1927");
        NAD1927BLMZone17N.getGeographicInfo().setName("GCS_North_American_1927");
        NAD1983HARNUTMZone11N.getGeographicInfo().setName("GCS_North_American_1983_HARN");
        NAD1983HARNUTMZone12N.getGeographicInfo().setName("GCS_North_American_1983_HARN");
        NAD1983HARNUTMZone13N.getGeographicInfo().setName("GCS_North_American_1983_HARN");
        NAD1983HARNUTMZone18N.getGeographicInfo().setName("GCS_North_American_1983_HARN");
        NAD1983HARNUTMZone2S.getGeographicInfo().setName("GCS_North_American_1983_HARN");
        NAD1983HARNUTMZone4N.getGeographicInfo().setName("GCS_North_American_1983_HARN");
        NAD1983HARNUTMZone5N.getGeographicInfo().setName("GCS_North_American_1983_HARN");
        Nahrwan1967UTMZone38N.getGeographicInfo().setName("GCS_Nahrwan_1967");
        Nahrwan1967UTMZone39N.getGeographicInfo().setName("GCS_Nahrwan_1967");
        Nahrwan1967UTMZone40N.getGeographicInfo().setName("GCS_Nahrwan_1967");
        Naparima1955UTMZone20N.getGeographicInfo().setName("GCS_Naparima_1955");
        Naparima1972UTMZone20N.getGeographicInfo().setName("GCS_Naparima_1972");
        NEA74NoumeaUTMZone58S.getGeographicInfo().setName("GCS_NEA74_Noumea");
        NGNUTMZone38N.getGeographicInfo().setName("GCS_NGN");
        NGNUTMZone39N.getGeographicInfo().setName("GCS_NGN");
        NGO1948UTMZone32N.getGeographicInfo().setName("GCS_NGO_1948");
        NGO1948UTMZone33N.getGeographicInfo().setName("GCS_NGO_1948");
        NGO1948UTMZone34N.getGeographicInfo().setName("GCS_NGO_1948");
        NGO1948UTMZone35N.getGeographicInfo().setName("GCS_NGO_1948");
        NordSahara1959UTMZone29N.getGeographicInfo().setName("GCS_Nord_Sahara_1959");
        NordSahara1959UTMZone30N.getGeographicInfo().setName("GCS_Nord_Sahara_1959");
        NordSahara1959UTMZone31N.getGeographicInfo().setName("GCS_Nord_Sahara_1959");
        NordSahara1959UTMZone32N.getGeographicInfo().setName("GCS_Nord_Sahara_1959");
        NZGD1949UTMZone58S.getGeographicInfo().setName("GCS_New_Zealand_1949");
        NZGD1949UTMZone59S.getGeographicInfo().setName("GCS_New_Zealand_1949");
        NZGD1949UTMZone60S.getGeographicInfo().setName("GCS_New_Zealand_1949");
        NZGD2000UTMZone58S.getGeographicInfo().setName("GCS_NZGD_2000");
        NZGD2000UTMZone59S.getGeographicInfo().setName("GCS_NZGD_2000");
        NZGD2000UTMZone60S.getGeographicInfo().setName("GCS_NZGD_2000");
        ObservMeteorologico1939UTMZone25N.getGeographicInfo().setName("GCS_Observ_Meteorologico_1939");
        OldHawaiianUTMZone4N.getGeographicInfo().setName("GCS_Old_Hawaiian");
        OldHawaiianUTMZone5N.getGeographicInfo().setName("GCS_Old_Hawaiian");
        PDO1993UTMZone39N.getGeographicInfo().setName("GCS_PDO_1993");
        PDO1993UTMZone40N.getGeographicInfo().setName("GCS_PDO_1993");
        PointeNoireUTMZone32S.getGeographicInfo().setName("GCS_Pointe_Noire");
        PortoSanto1936UTMZone28N.getGeographicInfo().setName("GCS_Porto_Santo_1936");
        PortoSanto1995UTMZone28N.getGeographicInfo().setName("GCS_Porto_Santo_1995");
        ProvSAmerDatumUTMZone17s.getGeographicInfo().setName("GCS_Provisional_S_American_1956");
        ProvSAmerDatumUTMZone18N.getGeographicInfo().setName("GCS_Provisional_S_American_1956");
        ProvSAmerDatumUTMZone18S.getGeographicInfo().setName("GCS_Provisional_S_American_1956");
        ProvSAmerDatumUTMZone19N.getGeographicInfo().setName("GCS_Provisional_S_American_1956");
        ProvSAmerDatumUTMZone19S.getGeographicInfo().setName("GCS_Provisional_S_American_1956");
        ProvSAmerDatumUTMZone20N.getGeographicInfo().setName("GCS_Provisional_S_American_1956");
        ProvSAmerDatumUTMZone20S.getGeographicInfo().setName("GCS_Provisional_S_American_1956");
        ProvSAmerDatumUTMZone21N.getGeographicInfo().setName("GCS_Provisional_S_American_1956");
        ProvSAmerDatumUTMZone22S.getGeographicInfo().setName("GCS_Provisional_S_American_1956");
        PuertoRicoUTMZone20N.getGeographicInfo().setName("GCS_Puerto_Rico");
        Qornoq1927UTMZone22N.getGeographicInfo().setName("GCS_Qornoq_1927");
        Qornoq1927UTMZone23N.getGeographicInfo().setName("GCS_Qornoq_1927");
        REGVENUTMZone18N.getGeographicInfo().setName("GCS_REGVEN");
        REGVENUTMZone19N.getGeographicInfo().setName("GCS_REGVEN");
        REGVENUTMZone20N.getGeographicInfo().setName("GCS_REGVEN");
        RGFG1995UTMZone22N.getGeographicInfo().setName("GCS_RGFG_1995");
        RGR1992UTMZone40S.getGeographicInfo().setName("GCS_RGR_1992");
        RRAF1991UTMZone20N.getGeographicInfo().setName("GCS_RRAF_1991");
        SainteAnneUTMZone20N.getGeographicInfo().setName("GCS_Sainte_Anne");
        SambojaUTMZone50S.getGeographicInfo().setName("GCS_Samboja");
        SaoBrazUTMZone26N.getGeographicInfo().setName("GCS_Sao_Braz");
        SapperHill1943UTMZone20S.getGeographicInfo().setName("GCS_Sapper_Hill_1943");
        SapperHill1943UTMZone21S.getGeographicInfo().setName("GCS_Sapper_Hill_1943");
        SchwarzeckUTMZone33S.getGeographicInfo().setName("GCS_Schwarzeck");
        SelvagemGrande1938UTMZone28N.getGeographicInfo().setName("GCS_Selvagem_Grande_1938");
        SierraLeone1968UTMZone28N.getGeographicInfo().setName("GCS_Sierra_Leone_1968");
        SierraLeone1968UTMZone29N.getGeographicInfo().setName("GCS_Sierra_Leone_1968");
        SIRGASUTMZone17N.getGeographicInfo().setName("GCS_SIRGAS_2000");
        SIRGASUTMZone17S.getGeographicInfo().setName("GCS_SIRGAS_2000");
        SIRGASUTMZone18N.getGeographicInfo().setName("GCS_SIRGAS_2000");
        SIRGASUTMZone18S.getGeographicInfo().setName("GCS_SIRGAS_2000");
        SIRGASUTMZone19N.getGeographicInfo().setName("GCS_SIRGAS_2000");
        SIRGASUTMZone19S.getGeographicInfo().setName("GCS_SIRGAS_2000");
        SIRGASUTMZone20N.getGeographicInfo().setName("GCS_SIRGAS_2000");
        SIRGASUTMZone20S.getGeographicInfo().setName("GCS_SIRGAS_2000");
        SIRGASUTMZone21N.getGeographicInfo().setName("GCS_SIRGAS_2000");
        SIRGASUTMZone21S.getGeographicInfo().setName("GCS_SIRGAS_2000");
        SIRGASUTMZone22N.getGeographicInfo().setName("GCS_SIRGAS_2000");
        SIRGASUTMZone22S.getGeographicInfo().setName("GCS_SIRGAS_2000");
        SIRGASUTMZone23S.getGeographicInfo().setName("GCS_SIRGAS_2000");
        SIRGASUTMZone24S.getGeographicInfo().setName("GCS_SIRGAS_2000");
        SIRGASUTMZone25S.getGeographicInfo().setName("GCS_SIRGAS_2000");
        SouthAmerican1969UTMZone17S.getGeographicInfo().setName("GCS_SIRGAS");
        SouthAmerican1969UTMZone18N.getGeographicInfo().setName("GCS_SIRGAS");
        SouthAmerican1969UTMZone18S.getGeographicInfo().setName("GCS_SIRGAS");
        SouthAmerican1969UTMZone19N.getGeographicInfo().setName("GCS_SIRGAS");
        SouthAmerican1969UTMZone19S.getGeographicInfo().setName("GCS_SIRGAS");
        SouthAmerican1969UTMZone20N.getGeographicInfo().setName("GCS_SIRGAS");
        SouthAmerican1969UTMZone20S.getGeographicInfo().setName("GCS_SIRGAS");
        SouthAmerican1969UTMZone21N.getGeographicInfo().setName("GCS_SIRGAS");
        SouthAmerican1969UTMZone21S.getGeographicInfo().setName("GCS_SIRGAS");
        SouthAmerican1969UTMZone22N.getGeographicInfo().setName("GCS_SIRGAS");
        SouthAmerican1969UTMZone22S.getGeographicInfo().setName("GCS_SIRGAS");
        SouthAmerican1969UTMZone23S.getGeographicInfo().setName("GCS_SIRGAS");
        SouthAmerican1969UTMZone24S.getGeographicInfo().setName("GCS_SIRGAS");
        SouthAmerican1969UTMZone25S.getGeographicInfo().setName("GCS_SIRGAS");
        ST71BelepUTMZone58S.getGeographicInfo().setName("GCS_ST71_Belep");
        ST84IledesPinsUTMZone58S.getGeographicInfo().setName("GCS_ST84_Ile_des_Pins");
        ST87OuveaUTMZone58S.getGeographicInfo().setName("GCS_ST87_Ouvea");
        SudanUTMZone35N.getGeographicInfo().setName("GCS_Sudan");
        SudanUTMZone36N.getGeographicInfo().setName("GCS_Sudan");
        TahaaUTMZone5S.getGeographicInfo().setName("GCS_Tahaa");
        TahitiUTMZone6S.getGeographicInfo().setName("GCS_Tahiti_1979");
        Tananarive1925UTMZone38S.getGeographicInfo().setName("GCS_Tananarive_1925");
        Tananarive1925UTMZone39S.getGeographicInfo().setName("GCS_Tananarive_1925");
        TeteUTMZone36S.getGeographicInfo().setName("GCS_Tete");
        TeteUTMZone37S.getGeographicInfo().setName("GCS_Tete");
        Timbalai1948UTMZone49N.getGeographicInfo().setName("GCS_Timbalai_1948");
        Timbalai1948UTMZone50N.getGeographicInfo().setName("GCS_Timbalai_1948");
        TokyoUTMZone51N.getGeographicInfo().setName("GCS_Tokyo");
        TokyoUTMZone52N.getGeographicInfo().setName("GCS_Tokyo");
        TokyoUTMZone53N.getGeographicInfo().setName("GCS_Tokyo");
        TokyoUTMZone54N.getGeographicInfo().setName("GCS_Tokyo");
        TokyoUTMZone55N.getGeographicInfo().setName("GCS_Tokyo");
        TokyoUTMZone56N.getGeographicInfo().setName("GCS_Tokyo");
        TrucialCoast1948UTMZone39N.getGeographicInfo().setName("GCS_Trucial_Coast_1948");
        TrucialCoast1948UTMZone40N.getGeographicInfo().setName("GCS_Trucial_Coast_1948");
        YemenNGN1996UTMZone38N.getGeographicInfo().setName("GCS_Yemen_NGN_1996");
        YemenNGN1996UTMZone39N.getGeographicInfo().setName("GCS_Yemen_NGN_1996");
        Yoff1972UTMZone28N.getGeographicInfo().setName("GCS_Yoff");
        Zanderij1972UTMZone21N.getGeographicInfo().setName("GCS_Zanderij");

        Abidjan1987UTMZone29N.getGeographicInfo().getDatum().setName("D_Abidjan_1987");
        Abidjan1987UTMZone30N.getGeographicInfo().getDatum().setName("D_Abidjan_1987");
        AdindanUTMZone37N.getGeographicInfo().getDatum().setName("D_Adindan");
        AdindanUTMZone38N.getGeographicInfo().getDatum().setName("D_Adindan");
        AfgooyeUTMZone38N.getGeographicInfo().getDatum().setName("D_Afgooye");
        AfgooyeUTMZone39N.getGeographicInfo().getDatum().setName("D_Afgooye");
        AinelAbd1970UTMZone37N.getGeographicInfo().getDatum().setName("D_Ain_el_Abd_1970");
        AinelAbd1970UTMZone38N.getGeographicInfo().getDatum().setName("D_Ain_el_Abd_1970");
        AinelAbd1970UTMZone39N.getGeographicInfo().getDatum().setName("D_Ain_el_Abd_1970");
        AmericanSamoa1962UTMZone2S.getGeographicInfo().getDatum().setName("D_American_Samoa_1962");
        AratuUTMZone22S.getGeographicInfo().getDatum().setName("D_Aratu");
        AratuUTMZone23S.getGeographicInfo().getDatum().setName("D_Aratu");
        AratuUTMZone24S.getGeographicInfo().getDatum().setName("D_Aratu");
        Arc1950UTMZone34S.getGeographicInfo().getDatum().setName("D_Arc_1950");
        Arc1950UTMZone35S.getGeographicInfo().getDatum().setName("D_Arc_1950");
        Arc1950UTMZone36S.getGeographicInfo().getDatum().setName("D_Arc_1950");
        Arc1960UTMZone35N.getGeographicInfo().getDatum().setName("D_Arc_1960");
        Arc1960UTMZone35S.getGeographicInfo().getDatum().setName("D_Arc_1960");
        Arc1960UTMZone36N.getGeographicInfo().getDatum().setName("D_Arc_1960");
        Arc1960UTMZone36S.getGeographicInfo().getDatum().setName("D_Arc_1960");
        Arc1960UTMZone37N.getGeographicInfo().getDatum().setName("D_Arc_1960");
        Arc1960UTMZone37S.getGeographicInfo().getDatum().setName("D_Arc_1960");
        ATS1977UTMZone19N.getGeographicInfo().getDatum().setName("D_ATS_1977");
        ATS1977UTMZone20N.getGeographicInfo().getDatum().setName("D_ATS_1977");
        AzoresCentral1995UTMZone26N.getGeographicInfo().getDatum().setName("D_Azores_Central_Islands_1995");
        AzoresOriental1995UTMZone26N.getGeographicInfo().getDatum().setName("D_Azores_Oriental_Islands_1995");
        BataviaUTMZone48S.getGeographicInfo().getDatum().setName("D_Batavia");
        BataviaUTMZone49S.getGeographicInfo().getDatum().setName("D_Batavia");
        BataviaUTMZone50S.getGeographicInfo().getDatum().setName("D_Batavia");
        BissauUTMZone28N.getGeographicInfo().getDatum().setName("D_Bissau");
        BogotaUTMZone17N.getGeographicInfo().getDatum().setName("D_Bogota");
        BogotaUTMZone18N.getGeographicInfo().getDatum().setName("D_Bogota");
        CamacupaUTMZone32S.getGeographicInfo().getDatum().setName("D_Camacupa");
        CamacupaUTMZone33S.getGeographicInfo().getDatum().setName("D_Camacupa");
        CampoInchauspeUTM19S.getGeographicInfo().getDatum().setName("D_Campo_Inchauspe");
        CampoInchauspeUTM20S.getGeographicInfo().getDatum().setName("D_Campo_Inchauspe");
        CapeUTMZone34S.getGeographicInfo().getDatum().setName("D_Cape");
        CapeUTMZone35S.getGeographicInfo().getDatum().setName("D_Cape");
        CapeUTMZone36S.getGeographicInfo().getDatum().setName("D_Cape");
        CarthageUTMZone32N.getGeographicInfo().getDatum().setName("D_Carthage");
        Combani1950UTMZone38S.getGeographicInfo().getDatum().setName("D_Combani_1950");
        Conakry1905UTMZone28N.getGeographicInfo().getDatum().setName("D_Conakry_1905");
        Conakry1905UTMZone29N.getGeographicInfo().getDatum().setName("D_Conakry_1905");
        CorregoAlegreUTMZone23S.getGeographicInfo().getDatum().setName("D_Corrego_Alegre");
        CorregoAlegreUTMZone24S.getGeographicInfo().getDatum().setName("D_Corrego_Alegre");
        CSG1967UTMZone22N.getGeographicInfo().getDatum().setName("D_CSG_1967");
        DabolaUTMZone28N.getGeographicInfo().getDatum().setName("D_Dabola_1981");
        DabolaUTMZone29N.getGeographicInfo().getDatum().setName("D_Dabola_1981");
        Datum73UTMZone29N.getGeographicInfo().getDatum().setName("D_Datum_73");
        DoualaUTMZone32N.getGeographicInfo().getDatum().setName("D_Douala");
        ED1950ED77UTMZone38N.getGeographicInfo().getDatum().setName("D_European_1950_ED77");
        ED1950ED77UTMZone39N.getGeographicInfo().getDatum().setName("D_European_1950_ED77");
        ED1950ED77UTMZone40N.getGeographicInfo().getDatum().setName("D_European_1950_ED77");
        ED1950ED77UTMZone41N.getGeographicInfo().getDatum().setName("D_European_1950_ED77");
        ELD1979UTMZone32N.getGeographicInfo().getDatum().setName("D_European_Libyan_1979");
        ELD1979UTMZone33N.getGeographicInfo().getDatum().setName("D_European_Libyan_1979");
        ELD1979UTMZone34N.getGeographicInfo().getDatum().setName("D_European_Libyan_1979");
        ELD1979UTMZone35N.getGeographicInfo().getDatum().setName("D_European_Libyan_1979");
        ETRF1989UTMZone28N.getGeographicInfo().getDatum().setName("D_ETRF_1989");
        ETRF1989UTMZone29N.getGeographicInfo().getDatum().setName("D_ETRF_1989");
        ETRF1989UTMZone30N.getGeographicInfo().getDatum().setName("D_ETRF_1989");
        ETRF1989UTMZone31N.getGeographicInfo().getDatum().setName("D_ETRF_1989");
        ETRF1989UTMZone32N.getGeographicInfo().getDatum().setName("D_ETRF_1989");
        ETRF1989UTMZone33N.getGeographicInfo().getDatum().setName("D_ETRF_1989");
        ETRF1989UTMZone34N.getGeographicInfo().getDatum().setName("D_ETRF_1989");
        ETRF1989UTMZone35N.getGeographicInfo().getDatum().setName("D_ETRF_1989");
        ETRF1989UTMZone36N.getGeographicInfo().getDatum().setName("D_ETRF_1989");
        ETRF1989UTMZone37N.getGeographicInfo().getDatum().setName("D_ETRF_1989");
        ETRF1989UTMZone38N.getGeographicInfo().getDatum().setName("D_ETRF_1989");
        ETRS1989UTMZone26N.getGeographicInfo().getDatum().setName("D_ETRS_1989");
        ETRS1989UTMZone27N.getGeographicInfo().getDatum().setName("D_ETRS_1989");
        ETRS1989UTMZone28N.getGeographicInfo().getDatum().setName("D_ETRS_1989");
        ETRS1989UTMZone29N.getGeographicInfo().getDatum().setName("D_ETRS_1989");
        ETRS1989UTMZone30N.getGeographicInfo().getDatum().setName("D_ETRS_1989");
        ETRS1989UTMZone31N.getGeographicInfo().getDatum().setName("D_ETRS_1989");
        ETRS1989UTMZone32N.getGeographicInfo().getDatum().setName("D_ETRS_1989");
        ETRS1989UTMZone33N.getGeographicInfo().getDatum().setName("D_ETRS_1989");
        ETRS1989UTMZone34N.getGeographicInfo().getDatum().setName("D_ETRS_1989");
        ETRS1989UTMZone35N.getGeographicInfo().getDatum().setName("D_ETRS_1989");
        ETRS1989UTMZone36N.getGeographicInfo().getDatum().setName("D_ETRS_1989");
        ETRS1989UTMZone37N.getGeographicInfo().getDatum().setName("D_ETRS_1989");
        ETRS1989UTMZone38N.getGeographicInfo().getDatum().setName("D_ETRS_1989");
        ETRS1989UTMZone39N.getGeographicInfo().getDatum().setName("D_ETRS_1989");
        EuropeanDatum1950UTMZone28N.getGeographicInfo().getDatum().setName("D_European_1950");
        EuropeanDatum1950UTMZone29N.getGeographicInfo().getDatum().setName("D_European_1950");
        EuropeanDatum1950UTMZone30N.getGeographicInfo().getDatum().setName("D_European_1950");
        EuropeanDatum1950UTMZone31N.getGeographicInfo().getDatum().setName("D_European_1950");
        EuropeanDatum1950UTMZone32N.getGeographicInfo().getDatum().setName("D_European_1950");
        EuropeanDatum1950UTMZone33N.getGeographicInfo().getDatum().setName("D_European_1950");
        EuropeanDatum1950UTMZone34N.getGeographicInfo().getDatum().setName("D_European_1950");
        EuropeanDatum1950UTMZone35N.getGeographicInfo().getDatum().setName("D_European_1950");
        EuropeanDatum1950UTMZone36N.getGeographicInfo().getDatum().setName("D_European_1950");
        EuropeanDatum1950UTMZone37N.getGeographicInfo().getDatum().setName("D_European_1950");
        EuropeanDatum1950UTMZone38N.getGeographicInfo().getDatum().setName("D_European_1950");
        EuropeanDatum1950UTMZone28N.setScaleFactor(0.9996);
        EuropeanDatum1950UTMZone29N.setScaleFactor(0.9996);
        EuropeanDatum1950UTMZone30N.setScaleFactor(0.9996);
        EuropeanDatum1950UTMZone31N.setScaleFactor(0.9996);
        EuropeanDatum1950UTMZone32N.setScaleFactor(0.9996);
        EuropeanDatum1950UTMZone33N.setScaleFactor(0.9996);
        EuropeanDatum1950UTMZone34N.setScaleFactor(0.9996);
        EuropeanDatum1950UTMZone35N.setScaleFactor(0.9996);
        EuropeanDatum1950UTMZone36N.setScaleFactor(0.9996);
        EuropeanDatum1950UTMZone37N.setScaleFactor(0.9996);
        EuropeanDatum1950UTMZone38N.setScaleFactor(0.9996);
        FahudUTMZone39N.getGeographicInfo().getDatum().setName("D_Fahud");
        FahudUTMZone40N.getGeographicInfo().getDatum().setName("D_Fahud");
        FortDesaixUTMZone20N.getGeographicInfo().getDatum().setName("D_Fort_Desaix");
        FortMarigotUTMZone20N.getGeographicInfo().getDatum().setName("D_Fort_Marigot");
        GarouaUTMZone33N.getGeographicInfo().getDatum().setName("D_Garoua");
        GraciosaBaseSW1948UTMZone26N.getGeographicInfo().getDatum().setName("D_Graciosa_Base_SW_1948");
        GrandComorosUTMZone38S.getGeographicInfo().getDatum().setName("D_Grand_Comoros");
        HitoXVIII1963UTMZone19S.getGeographicInfo().getDatum().setName("D_Hito_XVIII_1963");
        Hjorsey1955UTMZone26N.getGeographicInfo().getDatum().setName("D_Hjorsey_1955");
        Hjorsey1955UTMZone27N.getGeographicInfo().getDatum().setName("D_Hjorsey_1955");
        Hjorsey1955UTMZone28N.getGeographicInfo().getDatum().setName("D_Hjorsey_1955");
        HongKong1980UTMZone49N.getGeographicInfo().getDatum().setName("D_Hong_Kong_1980");
        HongKong1980UTMZone50N.getGeographicInfo().getDatum().setName("D_Hong_Kong_1980");
        IGM1995UTMZone32N.getGeographicInfo().getDatum().setName("D_IGM_1995");
        IGM1995UTMZone33N.getGeographicInfo().getDatum().setName("D_IGM_1995");
        IGN53MareUTMZone58S.getGeographicInfo().getDatum().setName("D_IGN53_Mare");
        IGN56LifouUTMZone58S.getGeographicInfo().getDatum().setName("D_IGN56_Lifou");
        IGN72GrandeTerreUTMZone58S.getGeographicInfo().getDatum().setName("D_IGN72_Grande_Terre");
        IGN72NukuHivaUTMZone7S.getGeographicInfo().getDatum().setName("D_IGN72_Nuku_Hiva");
        Indian1954UTMZone46N.getGeographicInfo().getDatum().setName("D_Indian_1954");
        Indian1954UTMZone47N.getGeographicInfo().getDatum().setName("D_Indian_1954");
        Indian1954UTMZone48N.getGeographicInfo().getDatum().setName("D_Indian_1954");
        Indian1960UTMZone48N.getGeographicInfo().getDatum().setName("D_Indian_1960");
        Indian1960UTMZone49N.getGeographicInfo().getDatum().setName("D_Indian_1960");
        Indian1975UTMZone47N.getGeographicInfo().getDatum().setName("D_Indian_1975");
        Indian1975UTMZone48N.getGeographicInfo().getDatum().setName("D_Indian_1975");
        Indonesia1974UTMZone46N.getGeographicInfo().getDatum().setName("D_Indonesian_1974");
        Indonesia1974UTMZone46S.getGeographicInfo().getDatum().setName("D_Indonesian_1974");
        Indonesia1974UTMZone47N.getGeographicInfo().getDatum().setName("D_Indonesian_1974");
        Indonesia1974UTMZone47S.getGeographicInfo().getDatum().setName("D_Indonesian_1974");
        Indonesia1974UTMZone48N.getGeographicInfo().getDatum().setName("D_Indonesian_1974");
        Indonesia1974UTMZone48S.getGeographicInfo().getDatum().setName("D_Indonesian_1974");
        Indonesia1974UTMZone49N.getGeographicInfo().getDatum().setName("D_Indonesian_1974");
        Indonesia1974UTMZone49S.getGeographicInfo().getDatum().setName("D_Indonesian_1974");
        Indonesia1974UTMZone50N.getGeographicInfo().getDatum().setName("D_Indonesian_1974");
        Indonesia1974UTMZone50S.getGeographicInfo().getDatum().setName("D_Indonesian_1974");
        Indonesia1974UTMZone51N.getGeographicInfo().getDatum().setName("D_Indonesian_1974");
        Indonesia1974UTMZone51S.getGeographicInfo().getDatum().setName("D_Indonesian_1974");
        Indonesia1974UTMZone52N.getGeographicInfo().getDatum().setName("D_Indonesian_1974");
        Indonesia1974UTMZone52S.getGeographicInfo().getDatum().setName("D_Indonesian_1974");
        Indonesia1974UTMZone53N.getGeographicInfo().getDatum().setName("D_Indonesian_1974");
        Indonesia1974UTMZone53S.getGeographicInfo().getDatum().setName("D_Indonesian_1974");
        Indonesia1974UTMZone54S.getGeographicInfo().getDatum().setName("D_Indonesian_1974");
        IRENET95UTMZone29N.getGeographicInfo().getDatum().setName("D_IRENET95");
        JGD2000UTMZone51N.getGeographicInfo().getDatum().setName("D_JGD_2000");
        JGD2000UTMZone52N.getGeographicInfo().getDatum().setName("D_JGD_2000");
        JGD2000UTMZone53N.getGeographicInfo().getDatum().setName("D_JGD_2000");
        JGD2000UTMZone54N.getGeographicInfo().getDatum().setName("D_JGD_2000");
        JGD2000UTMZone55N.getGeographicInfo().getDatum().setName("D_JGD_2000");
        JGD2000UTMZone56N.getGeographicInfo().getDatum().setName("D_JGD_2000");
        K01949UTMZone42S.getGeographicInfo().getDatum().setName("D_K0_1949");
        KertauUTMZone47N.getGeographicInfo().getDatum().setName("D_Kertau");
        KertauUTMZone48N.getGeographicInfo().getDatum().setName("D_Kertau");
        KousseriUTMZone33N.getGeographicInfo().getDatum().setName("D_Kousseri");
        LaCanoaUTMZone18N.getGeographicInfo().getDatum().setName("D_La_Canoa");
        LaCanoaUTMZone19N.getGeographicInfo().getDatum().setName("D_La_Canoa");
        LaCanoaUTMZone20N.getGeographicInfo().getDatum().setName("D_La_Canoa");
        LaCanoaUTMZone21N.getGeographicInfo().getDatum().setName("D_La_Canoa");
        Locodjo1965UTMZone29N.getGeographicInfo().getDatum().setName("D_Locodjo_1965");
        Locodjo1965UTMZone30N.getGeographicInfo().getDatum().setName("D_Locodjo_1965");
        LomeUTMZone31N.getGeographicInfo().getDatum().setName("D_Lome");
        Malongo1987UTMZone32S.getGeographicInfo().getDatum().setName("D_Mporaloko");
        Manoca1962UTMZone32N.getGeographicInfo().getDatum().setName("D_Mporaloko");
        MassawaUTMZone37N.getGeographicInfo().getDatum().setName("D_Massawa");
        MhastUTMZone32S.getGeographicInfo().getDatum().setName("D_Mhast_Offshore");
        MinnaUTMZone31N.getGeographicInfo().getDatum().setName("D_Minna");
        MinnaUTMZone32N.getGeographicInfo().getDatum().setName("D_Minna");
        MOP78UTMZone1S.getGeographicInfo().getDatum().setName("D_MOP78");
        MoznetUTMZone36S.getGeographicInfo().getDatum().setName("D_Moznet");
        MoznetUTMZone37S.getGeographicInfo().getDatum().setName("D_Moznet");
        NAD1927BLMZone14N.getGeographicInfo().getDatum().setName("D_North_American_1927");
        NAD1927BLMZone15N.getGeographicInfo().getDatum().setName("D_North_American_1927");
        NAD1927BLMZone16N.getGeographicInfo().getDatum().setName("D_North_American_1927");
        NAD1927BLMZone17N.getGeographicInfo().getDatum().setName("D_North_American_1927");
        NAD1983HARNUTMZone11N.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
        NAD1983HARNUTMZone12N.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
        NAD1983HARNUTMZone13N.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
        NAD1983HARNUTMZone18N.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
        NAD1983HARNUTMZone2S.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
        NAD1983HARNUTMZone4N.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
        NAD1983HARNUTMZone5N.getGeographicInfo().getDatum().setName("D_North_American_1983_HARN");
        Nahrwan1967UTMZone38N.getGeographicInfo().getDatum().setName("D_Nahrwan_1967");
        Nahrwan1967UTMZone39N.getGeographicInfo().getDatum().setName("D_Nahrwan_1967");
        Nahrwan1967UTMZone40N.getGeographicInfo().getDatum().setName("D_Nahrwan_1967");
        Naparima1955UTMZone20N.getGeographicInfo().getDatum().setName("D_Naparima_1955");
        Naparima1972UTMZone20N.getGeographicInfo().getDatum().setName("D_Naparima_1972");
        NEA74NoumeaUTMZone58S.getGeographicInfo().getDatum().setName("D_NEA74_Noumea");
        NGNUTMZone38N.getGeographicInfo().getDatum().setName("D_NGN");
        NGNUTMZone39N.getGeographicInfo().getDatum().setName("D_NGN");
        NGO1948UTMZone32N.getGeographicInfo().getDatum().setName("D_NGO_1948");
        NGO1948UTMZone33N.getGeographicInfo().getDatum().setName("D_NGO_1948");
        NGO1948UTMZone34N.getGeographicInfo().getDatum().setName("D_NGO_1948");
        NGO1948UTMZone35N.getGeographicInfo().getDatum().setName("D_NGO_1948");
        NordSahara1959UTMZone29N.getGeographicInfo().getDatum().setName("D_Nord_Sahara_1959");
        NordSahara1959UTMZone30N.getGeographicInfo().getDatum().setName("D_Nord_Sahara_1959");
        NordSahara1959UTMZone31N.getGeographicInfo().getDatum().setName("D_Nord_Sahara_1959");
        NordSahara1959UTMZone32N.getGeographicInfo().getDatum().setName("D_Nord_Sahara_1959");
        NZGD1949UTMZone58S.getGeographicInfo().getDatum().setName("D_New_Zealand_1949");
        NZGD1949UTMZone59S.getGeographicInfo().getDatum().setName("D_New_Zealand_1949");
        NZGD1949UTMZone60S.getGeographicInfo().getDatum().setName("D_New_Zealand_1949");
        NZGD2000UTMZone58S.getGeographicInfo().getDatum().setName("D_NZGD_2000");
        NZGD2000UTMZone59S.getGeographicInfo().getDatum().setName("D_NZGD_2000");
        NZGD2000UTMZone60S.getGeographicInfo().getDatum().setName("D_NZGD_2000");
        ObservMeteorologico1939UTMZone25N.getGeographicInfo().getDatum().setName("D_Observ_Meteorologico_1939");
        OldHawaiianUTMZone4N.getGeographicInfo().getDatum().setName("D_Old_Hawaiian");
        OldHawaiianUTMZone5N.getGeographicInfo().getDatum().setName("D_Old_Hawaiian");
        PDO1993UTMZone39N.getGeographicInfo().getDatum().setName("D_PDO_1993");
        PDO1993UTMZone40N.getGeographicInfo().getDatum().setName("D_PDO_1993");
        PointeNoireUTMZone32S.getGeographicInfo().getDatum().setName("D_Pointe_Noire");
        PortoSanto1936UTMZone28N.getGeographicInfo().getDatum().setName("D_Porto_Santo_1936");
        PortoSanto1995UTMZone28N.getGeographicInfo().getDatum().setName("D_Porto_Santo_1995");
        ProvSAmerDatumUTMZone17s.getGeographicInfo().getDatum().setName("D_Provisional_S_American_1956");
        ProvSAmerDatumUTMZone18N.getGeographicInfo().getDatum().setName("D_Provisional_S_American_1956");
        ProvSAmerDatumUTMZone18S.getGeographicInfo().getDatum().setName("D_Provisional_S_American_1956");
        ProvSAmerDatumUTMZone19N.getGeographicInfo().getDatum().setName("D_Provisional_S_American_1956");
        ProvSAmerDatumUTMZone19S.getGeographicInfo().getDatum().setName("D_Provisional_S_American_1956");
        ProvSAmerDatumUTMZone20N.getGeographicInfo().getDatum().setName("D_Provisional_S_American_1956");
        ProvSAmerDatumUTMZone20S.getGeographicInfo().getDatum().setName("D_Provisional_S_American_1956");
        ProvSAmerDatumUTMZone21N.getGeographicInfo().getDatum().setName("D_Provisional_S_American_1956");
        ProvSAmerDatumUTMZone22S.getGeographicInfo().getDatum().setName("D_Provisional_S_American_1956");
        PuertoRicoUTMZone20N.getGeographicInfo().getDatum().setName("D_Puerto_Rico");
        Qornoq1927UTMZone22N.getGeographicInfo().getDatum().setName("D_Qornoq_1927");
        Qornoq1927UTMZone23N.getGeographicInfo().getDatum().setName("D_Qornoq_1927");
        REGVENUTMZone18N.getGeographicInfo().getDatum().setName("D_REGVEN");
        REGVENUTMZone19N.getGeographicInfo().getDatum().setName("D_REGVEN");
        REGVENUTMZone20N.getGeographicInfo().getDatum().setName("D_REGVEN");
        RGFG1995UTMZone22N.getGeographicInfo().getDatum().setName("D_RGFG_1995");
        RGR1992UTMZone40S.getGeographicInfo().getDatum().setName("D_RGR_1992");
        RRAF1991UTMZone20N.getGeographicInfo().getDatum().setName("D_RRAF_1991");
        SainteAnneUTMZone20N.getGeographicInfo().getDatum().setName("D_Sainte_Anne");
        SambojaUTMZone50S.getGeographicInfo().getDatum().setName("D_Samboja");
        SaoBrazUTMZone26N.getGeographicInfo().getDatum().setName("D_Sao_Braz");
        SapperHill1943UTMZone20S.getGeographicInfo().getDatum().setName("D_Sapper_Hill_1943");
        SapperHill1943UTMZone21S.getGeographicInfo().getDatum().setName("D_Sapper_Hill_1943");
        SchwarzeckUTMZone33S.getGeographicInfo().getDatum().setName("D_Schwarzeck");
        SelvagemGrande1938UTMZone28N.getGeographicInfo().getDatum().setName("D_Selvagem_Grande_1938");
        SierraLeone1968UTMZone28N.getGeographicInfo().getDatum().setName("D_Sierra_Leone_1968");
        SierraLeone1968UTMZone29N.getGeographicInfo().getDatum().setName("D_Sierra_Leone_1968");
        SIRGASUTMZone17N.getGeographicInfo().getDatum().setName("D_SIRGAS_2000");
        SIRGASUTMZone17S.getGeographicInfo().getDatum().setName("D_SIRGAS_2000");
        SIRGASUTMZone18N.getGeographicInfo().getDatum().setName("D_SIRGAS_2000");
        SIRGASUTMZone18S.getGeographicInfo().getDatum().setName("D_SIRGAS_2000");
        SIRGASUTMZone19N.getGeographicInfo().getDatum().setName("D_SIRGAS_2000");
        SIRGASUTMZone19S.getGeographicInfo().getDatum().setName("D_SIRGAS_2000");
        SIRGASUTMZone20N.getGeographicInfo().getDatum().setName("D_SIRGAS_2000");
        SIRGASUTMZone20S.getGeographicInfo().getDatum().setName("D_SIRGAS_2000");
        SIRGASUTMZone21N.getGeographicInfo().getDatum().setName("D_SIRGAS_2000");
        SIRGASUTMZone21S.getGeographicInfo().getDatum().setName("D_SIRGAS_2000");
        SIRGASUTMZone22N.getGeographicInfo().getDatum().setName("D_SIRGAS_2000");
        SIRGASUTMZone22S.getGeographicInfo().getDatum().setName("D_SIRGAS_2000");
        SIRGASUTMZone23S.getGeographicInfo().getDatum().setName("D_SIRGAS_2000");
        SIRGASUTMZone24S.getGeographicInfo().getDatum().setName("D_SIRGAS_2000");
        SIRGASUTMZone25S.getGeographicInfo().getDatum().setName("D_SIRGAS_2000");
        SouthAmerican1969UTMZone17S.getGeographicInfo().getDatum().setName("D_SIRGAS");
        SouthAmerican1969UTMZone18N.getGeographicInfo().getDatum().setName("D_SIRGAS");
        SouthAmerican1969UTMZone18S.getGeographicInfo().getDatum().setName("D_SIRGAS");
        SouthAmerican1969UTMZone19N.getGeographicInfo().getDatum().setName("D_SIRGAS");
        SouthAmerican1969UTMZone19S.getGeographicInfo().getDatum().setName("D_SIRGAS");
        SouthAmerican1969UTMZone20N.getGeographicInfo().getDatum().setName("D_SIRGAS");
        SouthAmerican1969UTMZone20S.getGeographicInfo().getDatum().setName("D_SIRGAS");
        SouthAmerican1969UTMZone21N.getGeographicInfo().getDatum().setName("D_SIRGAS");
        SouthAmerican1969UTMZone21S.getGeographicInfo().getDatum().setName("D_SIRGAS");
        SouthAmerican1969UTMZone22N.getGeographicInfo().getDatum().setName("D_SIRGAS");
        SouthAmerican1969UTMZone22S.getGeographicInfo().getDatum().setName("D_SIRGAS");
        SouthAmerican1969UTMZone23S.getGeographicInfo().getDatum().setName("D_SIRGAS");
        SouthAmerican1969UTMZone24S.getGeographicInfo().getDatum().setName("D_SIRGAS");
        SouthAmerican1969UTMZone25S.getGeographicInfo().getDatum().setName("D_SIRGAS");
        ST71BelepUTMZone58S.getGeographicInfo().getDatum().setName("D_ST71_Belep");
        ST84IledesPinsUTMZone58S.getGeographicInfo().getDatum().setName("D_ST84_Ile_des_Pins");
        ST87OuveaUTMZone58S.getGeographicInfo().getDatum().setName("D_ST87_Ouvea");
        SudanUTMZone35N.getGeographicInfo().getDatum().setName("D_Sudan");
        SudanUTMZone36N.getGeographicInfo().getDatum().setName("D_Sudan");
        TahaaUTMZone5S.getGeographicInfo().getDatum().setName("D_Tahaa");
        TahitiUTMZone6S.getGeographicInfo().getDatum().setName("D_Tahiti_1979");
        Tananarive1925UTMZone38S.getGeographicInfo().getDatum().setName("D_Tananarive_1925");
        Tananarive1925UTMZone39S.getGeographicInfo().getDatum().setName("D_Tananarive_1925");
        TeteUTMZone36S.getGeographicInfo().getDatum().setName("D_Tete");
        TeteUTMZone37S.getGeographicInfo().getDatum().setName("D_Tete");
        Timbalai1948UTMZone49N.getGeographicInfo().getDatum().setName("D_Timbalai_1948");
        Timbalai1948UTMZone50N.getGeographicInfo().getDatum().setName("D_Timbalai_1948");
        TokyoUTMZone51N.getGeographicInfo().getDatum().setName("D_Tokyo");
        TokyoUTMZone52N.getGeographicInfo().getDatum().setName("D_Tokyo");
        TokyoUTMZone53N.getGeographicInfo().getDatum().setName("D_Tokyo");
        TokyoUTMZone54N.getGeographicInfo().getDatum().setName("D_Tokyo");
        TokyoUTMZone55N.getGeographicInfo().getDatum().setName("D_Tokyo");
        TokyoUTMZone56N.getGeographicInfo().getDatum().setName("D_Tokyo");
        TrucialCoast1948UTMZone39N.getGeographicInfo().getDatum().setName("D_Trucial_Coast_1948");
        TrucialCoast1948UTMZone40N.getGeographicInfo().getDatum().setName("D_Trucial_Coast_1948");
        YemenNGN1996UTMZone38N.getGeographicInfo().getDatum().setName("D_Yemen_NGN_1996");
        YemenNGN1996UTMZone39N.getGeographicInfo().getDatum().setName("D_Yemen_NGN_1996");
        Yoff1972UTMZone28N.getGeographicInfo().getDatum().setName("D_Yoff");
        Zanderij1972UTMZone21N.getGeographicInfo().getDatum().setName("D_Zanderij");
    }

    //</editor-fold>

  /**
   * @return the ATS1977UTMZone19N
   */
  public ProjectionInfo getATS1977UTMZone19N() {
    return ATS1977UTMZone19N.copy();
  }

  /**
   * @return the ATS1977UTMZone20N
   */
  public ProjectionInfo getATS1977UTMZone20N() {
    return ATS1977UTMZone20N.copy();
  }

  /**
   * @return the Abidjan1987UTMZone29N
   */
  public ProjectionInfo getAbidjan1987UTMZone29N() {
    return Abidjan1987UTMZone29N.copy();
  }

  /**
   * @return the Abidjan1987UTMZone30N
   */
  public ProjectionInfo getAbidjan1987UTMZone30N() {
    return Abidjan1987UTMZone30N.copy();
  }

  /**
   * @return the AdindanUTMZone37N
   */
  public ProjectionInfo getAdindanUTMZone37N() {
    return AdindanUTMZone37N.copy();
  }

  /**
   * @return the AdindanUTMZone38N
   */
  public ProjectionInfo getAdindanUTMZone38N() {
    return AdindanUTMZone38N.copy();
  }

  /**
   * @return the AfgooyeUTMZone38N
   */
  public ProjectionInfo getAfgooyeUTMZone38N() {
    return AfgooyeUTMZone38N.copy();
  }

  /**
   * @return the AfgooyeUTMZone39N
   */
  public ProjectionInfo getAfgooyeUTMZone39N() {
    return AfgooyeUTMZone39N.copy();
  }

  /**
   * @return the AinelAbd1970UTMZone37N
   */
  public ProjectionInfo getAinelAbd1970UTMZone37N() {
    return AinelAbd1970UTMZone37N.copy();
  }

  /**
   * @return the AinelAbd1970UTMZone38N
   */
  public ProjectionInfo getAinelAbd1970UTMZone38N() {
    return AinelAbd1970UTMZone38N.copy();
  }

  /**
   * @return the AinelAbd1970UTMZone39N
   */
  public ProjectionInfo getAinelAbd1970UTMZone39N() {
    return AinelAbd1970UTMZone39N.copy();
  }

  /**
   * @return the AmericanSamoa1962UTMZone2S
   */
  public ProjectionInfo getAmericanSamoa1962UTMZone2S() {
    return AmericanSamoa1962UTMZone2S.copy();
  }

  /**
   * @return the AratuUTMZone22S
   */
  public ProjectionInfo getAratuUTMZone22S() {
    return AratuUTMZone22S.copy();
  }

  /**
   * @return the AratuUTMZone23S
   */
  public ProjectionInfo getAratuUTMZone23S() {
    return AratuUTMZone23S.copy();
  }

  /**
   * @return the AratuUTMZone24S
   */
  public ProjectionInfo getAratuUTMZone24S() {
    return AratuUTMZone24S.copy();
  }

  /**
   * @return the Arc1950UTMZone34S
   */
  public ProjectionInfo getArc1950UTMZone34S() {
    return Arc1950UTMZone34S.copy();
  }

  /**
   * @return the Arc1950UTMZone35S
   */
  public ProjectionInfo getArc1950UTMZone35S() {
    return Arc1950UTMZone35S.copy();
  }

  /**
   * @return the Arc1950UTMZone36S
   */
  public ProjectionInfo getArc1950UTMZone36S() {
    return Arc1950UTMZone36S.copy();
  }

  /**
   * @return the Arc1960UTMZone35N
   */
  public ProjectionInfo getArc1960UTMZone35N() {
    return Arc1960UTMZone35N.copy();
  }

  /**
   * @return the Arc1960UTMZone35S
   */
  public ProjectionInfo getArc1960UTMZone35S() {
    return Arc1960UTMZone35S.copy();
  }

  /**
   * @return the Arc1960UTMZone36N
   */
  public ProjectionInfo getArc1960UTMZone36N() {
    return Arc1960UTMZone36N.copy();
  }

  /**
   * @return the Arc1960UTMZone36S
   */
  public ProjectionInfo getArc1960UTMZone36S() {
    return Arc1960UTMZone36S.copy();
  }

  /**
   * @return the Arc1960UTMZone37N
   */
  public ProjectionInfo getArc1960UTMZone37N() {
    return Arc1960UTMZone37N.copy();
  }

  /**
   * @return the Arc1960UTMZone37S
   */
  public ProjectionInfo getArc1960UTMZone37S() {
    return Arc1960UTMZone37S.copy();
  }

  /**
   * @return the AzoresCentral1995UTMZone26N
   */
  public ProjectionInfo getAzoresCentral1995UTMZone26N() {
    return AzoresCentral1995UTMZone26N.copy();
  }

  /**
   * @return the AzoresOriental1995UTMZone26N
   */
  public ProjectionInfo getAzoresOriental1995UTMZone26N() {
    return AzoresOriental1995UTMZone26N.copy();
  }

  /**
   * @return the BataviaUTMZone48S
   */
  public ProjectionInfo getBataviaUTMZone48S() {
    return BataviaUTMZone48S.copy();
  }

  /**
   * @return the BataviaUTMZone49S
   */
  public ProjectionInfo getBataviaUTMZone49S() {
    return BataviaUTMZone49S.copy();
  }

  /**
   * @return the BataviaUTMZone50S
   */
  public ProjectionInfo getBataviaUTMZone50S() {
    return BataviaUTMZone50S.copy();
  }

  /**
   * @return the BissauUTMZone28N
   */
  public ProjectionInfo getBissauUTMZone28N() {
    return BissauUTMZone28N.copy();
  }

  /**
   * @return the BogotaUTMZone17N
   */
  public ProjectionInfo getBogotaUTMZone17N() {
    return BogotaUTMZone17N.copy();
  }

  /**
   * @return the BogotaUTMZone18N
   */
  public ProjectionInfo getBogotaUTMZone18N() {
    return BogotaUTMZone18N.copy();
  }

  /**
   * @return the CSG1967UTMZone22N
   */
  public ProjectionInfo getCSG1967UTMZone22N() {
    return CSG1967UTMZone22N.copy();
  }

  /**
   * @return the CamacupaUTMZone32S
   */
  public ProjectionInfo getCamacupaUTMZone32S() {
    return CamacupaUTMZone32S.copy();
  }

  /**
   * @return the CamacupaUTMZone33S
   */
  public ProjectionInfo getCamacupaUTMZone33S() {
    return CamacupaUTMZone33S.copy();
  }

  /**
   * @return the CampoInchauspeUTM19S
   */
  public ProjectionInfo getCampoInchauspeUTM19S() {
    return CampoInchauspeUTM19S.copy();
  }

  /**
   * @return the CampoInchauspeUTM20S
   */
  public ProjectionInfo getCampoInchauspeUTM20S() {
    return CampoInchauspeUTM20S.copy();
  }

  /**
   * @return the CapeUTMZone34S
   */
  public ProjectionInfo getCapeUTMZone34S() {
    return CapeUTMZone34S.copy();
  }

  /**
   * @return the CapeUTMZone35S
   */
  public ProjectionInfo getCapeUTMZone35S() {
    return CapeUTMZone35S.copy();
  }

  /**
   * @return the CapeUTMZone36S
   */
  public ProjectionInfo getCapeUTMZone36S() {
    return CapeUTMZone36S.copy();
  }

  /**
   * @return the CarthageUTMZone32N
   */
  public ProjectionInfo getCarthageUTMZone32N() {
    return CarthageUTMZone32N.copy();
  }

  /**
   * @return the Combani1950UTMZone38S
   */
  public ProjectionInfo getCombani1950UTMZone38S() {
    return Combani1950UTMZone38S.copy();
  }

  /**
   * @return the Conakry1905UTMZone28N
   */
  public ProjectionInfo getConakry1905UTMZone28N() {
    return Conakry1905UTMZone28N.copy();
  }

  /**
   * @return the Conakry1905UTMZone29N
   */
  public ProjectionInfo getConakry1905UTMZone29N() {
    return Conakry1905UTMZone29N.copy();
  }

  /**
   * @return the CorregoAlegreUTMZone23S
   */
  public ProjectionInfo getCorregoAlegreUTMZone23S() {
    return CorregoAlegreUTMZone23S.copy();
  }

  /**
   * @return the CorregoAlegreUTMZone24S
   */
  public ProjectionInfo getCorregoAlegreUTMZone24S() {
    return CorregoAlegreUTMZone24S.copy();
  }

  /**
   * @return the DabolaUTMZone28N
   */
  public ProjectionInfo getDabolaUTMZone28N() {
    return DabolaUTMZone28N.copy();
  }

  /**
   * @return the DabolaUTMZone29N
   */
  public ProjectionInfo getDabolaUTMZone29N() {
    return DabolaUTMZone29N.copy();
  }

  /**
   * @return the Datum73UTMZone29N
   */
  public ProjectionInfo getDatum73UTMZone29N() {
    return Datum73UTMZone29N.copy();
  }

  /**
   * @return the DoualaUTMZone32N
   */
  public ProjectionInfo getDoualaUTMZone32N() {
    return DoualaUTMZone32N.copy();
  }

  /**
   * @return the ED1950ED77UTMZone38N
   */
  public ProjectionInfo getED1950ED77UTMZone38N() {
    return ED1950ED77UTMZone38N.copy();
  }

  /**
   * @return the ED1950ED77UTMZone39N
   */
  public ProjectionInfo getED1950ED77UTMZone39N() {
    return ED1950ED77UTMZone39N.copy();
  }

  /**
   * @return the ED1950ED77UTMZone40N
   */
  public ProjectionInfo getED1950ED77UTMZone40N() {
    return ED1950ED77UTMZone40N.copy();
  }

  /**
   * @return the ED1950ED77UTMZone41N
   */
  public ProjectionInfo getED1950ED77UTMZone41N() {
    return ED1950ED77UTMZone41N.copy();
  }

  /**
   * @return the ELD1979UTMZone32N
   */
  public ProjectionInfo getELD1979UTMZone32N() {
    return ELD1979UTMZone32N.copy();
  }

  /**
   * @return the ELD1979UTMZone33N
   */
  public ProjectionInfo getELD1979UTMZone33N() {
    return ELD1979UTMZone33N.copy();
  }

  /**
   * @return the ELD1979UTMZone34N
   */
  public ProjectionInfo getELD1979UTMZone34N() {
    return ELD1979UTMZone34N.copy();
  }

  /**
   * @return the ELD1979UTMZone35N
   */
  public ProjectionInfo getELD1979UTMZone35N() {
    return ELD1979UTMZone35N.copy();
  }

  /**
   * @return the ETRF1989UTMZone28N
   */
  public ProjectionInfo getETRF1989UTMZone28N() {
    return ETRF1989UTMZone28N.copy();
  }

  /**
   * @return the ETRF1989UTMZone29N
   */
  public ProjectionInfo getETRF1989UTMZone29N() {
    return ETRF1989UTMZone29N.copy();
  }

  /**
   * @return the ETRF1989UTMZone30N
   */
  public ProjectionInfo getETRF1989UTMZone30N() {
    return ETRF1989UTMZone30N.copy();
  }

  /**
   * @return the ETRF1989UTMZone31N
   */
  public ProjectionInfo getETRF1989UTMZone31N() {
    return ETRF1989UTMZone31N.copy();
  }

  /**
   * @return the ETRF1989UTMZone32N
   */
  public ProjectionInfo getETRF1989UTMZone32N() {
    return ETRF1989UTMZone32N.copy();
  }

  /**
   * @return the ETRF1989UTMZone33N
   */
  public ProjectionInfo getETRF1989UTMZone33N() {
    return ETRF1989UTMZone33N.copy();
  }

  /**
   * @return the ETRF1989UTMZone34N
   */
  public ProjectionInfo getETRF1989UTMZone34N() {
    return ETRF1989UTMZone34N.copy();
  }

  /**
   * @return the ETRF1989UTMZone35N
   */
  public ProjectionInfo getETRF1989UTMZone35N() {
    return ETRF1989UTMZone35N.copy();
  }

  /**
   * @return the ETRF1989UTMZone36N
   */
  public ProjectionInfo getETRF1989UTMZone36N() {
    return ETRF1989UTMZone36N.copy();
  }

  /**
   * @return the ETRF1989UTMZone37N
   */
  public ProjectionInfo getETRF1989UTMZone37N() {
    return ETRF1989UTMZone37N.copy();
  }

  /**
   * @return the ETRF1989UTMZone38N
   */
  public ProjectionInfo getETRF1989UTMZone38N() {
    return ETRF1989UTMZone38N.copy();
  }

  /**
   * @return the ETRS1989UTMZone26N
   */
  public ProjectionInfo getETRS1989UTMZone26N() {
    return ETRS1989UTMZone26N.copy();
  }

  /**
   * @return the ETRS1989UTMZone27N
   */
  public ProjectionInfo getETRS1989UTMZone27N() {
    return ETRS1989UTMZone27N.copy();
  }

  /**
   * @return the ETRS1989UTMZone28N
   */
  public ProjectionInfo getETRS1989UTMZone28N() {
    return ETRS1989UTMZone28N.copy();
  }

  /**
   * @return the ETRS1989UTMZone29N
   */
  public ProjectionInfo getETRS1989UTMZone29N() {
    return ETRS1989UTMZone29N.copy();
  }

  /**
   * @return the ETRS1989UTMZone30N
   */
  public ProjectionInfo getETRS1989UTMZone30N() {
    return ETRS1989UTMZone30N.copy();
  }

  /**
   * @return the ETRS1989UTMZone31N
   */
  public ProjectionInfo getETRS1989UTMZone31N() {
    return ETRS1989UTMZone31N.copy();
  }

  /**
   * @return the ETRS1989UTMZone32N
   */
  public ProjectionInfo getETRS1989UTMZone32N() {
    return ETRS1989UTMZone32N.copy();
  }

  /**
   * @return the ETRS1989UTMZone33N
   */
  public ProjectionInfo getETRS1989UTMZone33N() {
    return ETRS1989UTMZone33N.copy();
  }

  /**
   * @return the ETRS1989UTMZone34N
   */
  public ProjectionInfo getETRS1989UTMZone34N() {
    return ETRS1989UTMZone34N.copy();
  }

  /**
   * @return the ETRS1989UTMZone35N
   */
  public ProjectionInfo getETRS1989UTMZone35N() {
    return ETRS1989UTMZone35N.copy();
  }

  /**
   * @return the ETRS1989UTMZone36N
   */
  public ProjectionInfo getETRS1989UTMZone36N() {
    return ETRS1989UTMZone36N.copy();
  }

  /**
   * @return the ETRS1989UTMZone37N
   */
  public ProjectionInfo getETRS1989UTMZone37N() {
    return ETRS1989UTMZone37N.copy();
  }

  /**
   * @return the ETRS1989UTMZone38N
   */
  public ProjectionInfo getETRS1989UTMZone38N() {
    return ETRS1989UTMZone38N.copy();
  }

  /**
   * @return the ETRS1989UTMZone39N
   */
  public ProjectionInfo getETRS1989UTMZone39N() {
    return ETRS1989UTMZone39N.copy();
  }

  /**
   * @return the EuropeanDatum1950UTMZone28N
   */
  public ProjectionInfo getEuropeanDatum1950UTMZone28N() {
    return EuropeanDatum1950UTMZone28N.copy();
  }

  /**
   * @return the EuropeanDatum1950UTMZone29N
   */
  public ProjectionInfo getEuropeanDatum1950UTMZone29N() {
    return EuropeanDatum1950UTMZone29N.copy();
  }

  /**
   * @return the EuropeanDatum1950UTMZone30N
   */
  public ProjectionInfo getEuropeanDatum1950UTMZone30N() {
    return EuropeanDatum1950UTMZone30N.copy();
  }

  /**
   * @return the EuropeanDatum1950UTMZone31N
   */
  public ProjectionInfo getEuropeanDatum1950UTMZone31N() {
    return EuropeanDatum1950UTMZone31N.copy();
  }

  /**
   * @return the EuropeanDatum1950UTMZone32N
   */
  public ProjectionInfo getEuropeanDatum1950UTMZone32N() {
    return EuropeanDatum1950UTMZone32N.copy();
  }

  /**
   * @return the EuropeanDatum1950UTMZone33N
   */
  public ProjectionInfo getEuropeanDatum1950UTMZone33N() {
    return EuropeanDatum1950UTMZone33N.copy();
  }

  /**
   * @return the EuropeanDatum1950UTMZone34N
   */
  public ProjectionInfo getEuropeanDatum1950UTMZone34N() {
    return EuropeanDatum1950UTMZone34N.copy();
  }

  /**
   * @return the EuropeanDatum1950UTMZone35N
   */
  public ProjectionInfo getEuropeanDatum1950UTMZone35N() {
    return EuropeanDatum1950UTMZone35N.copy();
  }

  /**
   * @return the EuropeanDatum1950UTMZone36N
   */
  public ProjectionInfo getEuropeanDatum1950UTMZone36N() {
    return EuropeanDatum1950UTMZone36N.copy();
  }

  /**
   * @return the EuropeanDatum1950UTMZone37N
   */
  public ProjectionInfo getEuropeanDatum1950UTMZone37N() {
    return EuropeanDatum1950UTMZone37N.copy();
  }

  /**
   * @return the EuropeanDatum1950UTMZone38N
   */
  public ProjectionInfo getEuropeanDatum1950UTMZone38N() {
    return EuropeanDatum1950UTMZone38N.copy();
  }

  /**
   * @return the FahudUTMZone39N
   */
  public ProjectionInfo getFahudUTMZone39N() {
    return FahudUTMZone39N.copy();
  }

  /**
   * @return the FahudUTMZone40N
   */
  public ProjectionInfo getFahudUTMZone40N() {
    return FahudUTMZone40N.copy();
  }

  /**
   * @return the FortDesaixUTMZone20N
   */
  public ProjectionInfo getFortDesaixUTMZone20N() {
    return FortDesaixUTMZone20N.copy();
  }

  /**
   * @return the FortMarigotUTMZone20N
   */
  public ProjectionInfo getFortMarigotUTMZone20N() {
    return FortMarigotUTMZone20N.copy();
  }

  /**
   * @return the GarouaUTMZone33N
   */
  public ProjectionInfo getGarouaUTMZone33N() {
    return GarouaUTMZone33N.copy();
  }

  /**
   * @return the GraciosaBaseSW1948UTMZone26N
   */
  public ProjectionInfo getGraciosaBaseSW1948UTMZone26N() {
    return GraciosaBaseSW1948UTMZone26N.copy();
  }

  /**
   * @return the GrandComorosUTMZone38S
   */
  public ProjectionInfo getGrandComorosUTMZone38S() {
    return GrandComorosUTMZone38S.copy();
  }

  /**
   * @return the HitoXVIII1963UTMZone19S
   */
  public ProjectionInfo getHitoXVIII1963UTMZone19S() {
    return HitoXVIII1963UTMZone19S.copy();
  }

  /**
   * @return the Hjorsey1955UTMZone26N
   */
  public ProjectionInfo getHjorsey1955UTMZone26N() {
    return Hjorsey1955UTMZone26N.copy();
  }

  /**
   * @return the Hjorsey1955UTMZone27N
   */
  public ProjectionInfo getHjorsey1955UTMZone27N() {
    return Hjorsey1955UTMZone27N.copy();
  }

  /**
   * @return the Hjorsey1955UTMZone28N
   */
  public ProjectionInfo getHjorsey1955UTMZone28N() {
    return Hjorsey1955UTMZone28N.copy();
  }

  /**
   * @return the HongKong1980UTMZone49N
   */
  public ProjectionInfo getHongKong1980UTMZone49N() {
    return HongKong1980UTMZone49N.copy();
  }

  /**
   * @return the HongKong1980UTMZone50N
   */
  public ProjectionInfo getHongKong1980UTMZone50N() {
    return HongKong1980UTMZone50N.copy();
  }

  /**
   * @return the IGM1995UTMZone32N
   */
  public ProjectionInfo getIGM1995UTMZone32N() {
    return IGM1995UTMZone32N.copy();
  }

  /**
   * @return the IGM1995UTMZone33N
   */
  public ProjectionInfo getIGM1995UTMZone33N() {
    return IGM1995UTMZone33N.copy();
  }

  /**
   * @return the IGN53MareUTMZone58S
   */
  public ProjectionInfo getIGN53MareUTMZone58S() {
    return IGN53MareUTMZone58S.copy();
  }

  /**
   * @return the IGN56LifouUTMZone58S
   */
  public ProjectionInfo getIGN56LifouUTMZone58S() {
    return IGN56LifouUTMZone58S.copy();
  }

  /**
   * @return the IGN72GrandeTerreUTMZone58S
   */
  public ProjectionInfo getIGN72GrandeTerreUTMZone58S() {
    return IGN72GrandeTerreUTMZone58S.copy();
  }

  /**
   * @return the IGN72NukuHivaUTMZone7S
   */
  public ProjectionInfo getIGN72NukuHivaUTMZone7S() {
    return IGN72NukuHivaUTMZone7S.copy();
  }

  /**
   * @return the IRENET95UTMZone29N
   */
  public ProjectionInfo getIRENET95UTMZone29N() {
    return IRENET95UTMZone29N.copy();
  }

  /**
   * @return the Indian1954UTMZone46N
   */
  public ProjectionInfo getIndian1954UTMZone46N() {
    return Indian1954UTMZone46N.copy();
  }

  /**
   * @return the Indian1954UTMZone47N
   */
  public ProjectionInfo getIndian1954UTMZone47N() {
    return Indian1954UTMZone47N.copy();
  }

  /**
   * @return the Indian1954UTMZone48N
   */
  public ProjectionInfo getIndian1954UTMZone48N() {
    return Indian1954UTMZone48N.copy();
  }

  /**
   * @return the Indian1960UTMZone48N
   */
  public ProjectionInfo getIndian1960UTMZone48N() {
    return Indian1960UTMZone48N.copy();
  }

  /**
   * @return the Indian1960UTMZone49N
   */
  public ProjectionInfo getIndian1960UTMZone49N() {
    return Indian1960UTMZone49N.copy();
  }

  /**
   * @return the Indian1975UTMZone47N
   */
  public ProjectionInfo getIndian1975UTMZone47N() {
    return Indian1975UTMZone47N.copy();
  }

  /**
   * @return the Indian1975UTMZone48N
   */
  public ProjectionInfo getIndian1975UTMZone48N() {
    return Indian1975UTMZone48N.copy();
  }

  /**
   * @return the Indonesia1974UTMZone46N
   */
  public ProjectionInfo getIndonesia1974UTMZone46N() {
    return Indonesia1974UTMZone46N.copy();
  }

  /**
   * @return the Indonesia1974UTMZone46S
   */
  public ProjectionInfo getIndonesia1974UTMZone46S() {
    return Indonesia1974UTMZone46S.copy();
  }

  /**
   * @return the Indonesia1974UTMZone47N
   */
  public ProjectionInfo getIndonesia1974UTMZone47N() {
    return Indonesia1974UTMZone47N.copy();
  }

  /**
   * @return the Indonesia1974UTMZone47S
   */
  public ProjectionInfo getIndonesia1974UTMZone47S() {
    return Indonesia1974UTMZone47S.copy();
  }

  /**
   * @return the Indonesia1974UTMZone48N
   */
  public ProjectionInfo getIndonesia1974UTMZone48N() {
    return Indonesia1974UTMZone48N.copy();
  }

  /**
   * @return the Indonesia1974UTMZone48S
   */
  public ProjectionInfo getIndonesia1974UTMZone48S() {
    return Indonesia1974UTMZone48S.copy();
  }

  /**
   * @return the Indonesia1974UTMZone49N
   */
  public ProjectionInfo getIndonesia1974UTMZone49N() {
    return Indonesia1974UTMZone49N.copy();
  }

  /**
   * @return the Indonesia1974UTMZone49S
   */
  public ProjectionInfo getIndonesia1974UTMZone49S() {
    return Indonesia1974UTMZone49S.copy();
  }

  /**
   * @return the Indonesia1974UTMZone50N
   */
  public ProjectionInfo getIndonesia1974UTMZone50N() {
    return Indonesia1974UTMZone50N.copy();
  }

  /**
   * @return the Indonesia1974UTMZone50S
   */
  public ProjectionInfo getIndonesia1974UTMZone50S() {
    return Indonesia1974UTMZone50S.copy();
  }

  /**
   * @return the Indonesia1974UTMZone51N
   */
  public ProjectionInfo getIndonesia1974UTMZone51N() {
    return Indonesia1974UTMZone51N.copy();
  }

  /**
   * @return the Indonesia1974UTMZone51S
   */
  public ProjectionInfo getIndonesia1974UTMZone51S() {
    return Indonesia1974UTMZone51S.copy();
  }

  /**
   * @return the Indonesia1974UTMZone52N
   */
  public ProjectionInfo getIndonesia1974UTMZone52N() {
    return Indonesia1974UTMZone52N.copy();
  }

  /**
   * @return the Indonesia1974UTMZone52S
   */
  public ProjectionInfo getIndonesia1974UTMZone52S() {
    return Indonesia1974UTMZone52S.copy();
  }

  /**
   * @return the Indonesia1974UTMZone53N
   */
  public ProjectionInfo getIndonesia1974UTMZone53N() {
    return Indonesia1974UTMZone53N.copy();
  }

  /**
   * @return the Indonesia1974UTMZone53S
   */
  public ProjectionInfo getIndonesia1974UTMZone53S() {
    return Indonesia1974UTMZone53S.copy();
  }

  /**
   * @return the Indonesia1974UTMZone54S
   */
  public ProjectionInfo getIndonesia1974UTMZone54S() {
    return Indonesia1974UTMZone54S.copy();
  }

  /**
   * @return the JGD2000UTMZone51N
   */
  public ProjectionInfo getJGD2000UTMZone51N() {
    return JGD2000UTMZone51N.copy();
  }

  /**
   * @return the JGD2000UTMZone52N
   */
  public ProjectionInfo getJGD2000UTMZone52N() {
    return JGD2000UTMZone52N.copy();
  }

  /**
   * @return the JGD2000UTMZone53N
   */
  public ProjectionInfo getJGD2000UTMZone53N() {
    return JGD2000UTMZone53N.copy();
  }

  /**
   * @return the JGD2000UTMZone54N
   */
  public ProjectionInfo getJGD2000UTMZone54N() {
    return JGD2000UTMZone54N.copy();
  }

  /**
   * @return the JGD2000UTMZone55N
   */
  public ProjectionInfo getJGD2000UTMZone55N() {
    return JGD2000UTMZone55N.copy();
  }

  /**
   * @return the JGD2000UTMZone56N
   */
  public ProjectionInfo getJGD2000UTMZone56N() {
    return JGD2000UTMZone56N.copy();
  }

  /**
   * @return the K01949UTMZone42S
   */
  public ProjectionInfo getK01949UTMZone42S() {
    return K01949UTMZone42S.copy();
  }

  /**
   * @return the KertauUTMZone47N
   */
  public ProjectionInfo getKertauUTMZone47N() {
    return KertauUTMZone47N.copy();
  }

  /**
   * @return the KertauUTMZone48N
   */
  public ProjectionInfo getKertauUTMZone48N() {
    return KertauUTMZone48N.copy();
  }

  /**
   * @return the KousseriUTMZone33N
   */
  public ProjectionInfo getKousseriUTMZone33N() {
    return KousseriUTMZone33N.copy();
  }

  /**
   * @return the LaCanoaUTMZone18N
   */
  public ProjectionInfo getLaCanoaUTMZone18N() {
    return LaCanoaUTMZone18N.copy();
  }

  /**
   * @return the LaCanoaUTMZone19N
   */
  public ProjectionInfo getLaCanoaUTMZone19N() {
    return LaCanoaUTMZone19N.copy();
  }

  /**
   * @return the LaCanoaUTMZone20N
   */
  public ProjectionInfo getLaCanoaUTMZone20N() {
    return LaCanoaUTMZone20N.copy();
  }

  /**
   * @return the LaCanoaUTMZone21N
   */
  public ProjectionInfo getLaCanoaUTMZone21N() {
    return LaCanoaUTMZone21N.copy();
  }

  /**
   * @return the Locodjo1965UTMZone29N
   */
  public ProjectionInfo getLocodjo1965UTMZone29N() {
    return Locodjo1965UTMZone29N.copy();
  }

  /**
   * @return the Locodjo1965UTMZone30N
   */
  public ProjectionInfo getLocodjo1965UTMZone30N() {
    return Locodjo1965UTMZone30N.copy();
  }

  /**
   * @return the LomeUTMZone31N
   */
  public ProjectionInfo getLomeUTMZone31N() {
    return LomeUTMZone31N.copy();
  }

  /**
   * @return the MOP78UTMZone1S
   */
  public ProjectionInfo getMOP78UTMZone1S() {
    return MOP78UTMZone1S.copy();
  }

  /**
   * @return the Malongo1987UTMZone32S
   */
  public ProjectionInfo getMalongo1987UTMZone32S() {
    return Malongo1987UTMZone32S.copy();
  }

  /**
   * @return the Manoca1962UTMZone32N
   */
  public ProjectionInfo getManoca1962UTMZone32N() {
    return Manoca1962UTMZone32N.copy();
  }

  /**
   * @return the MassawaUTMZone37N
   */
  public ProjectionInfo getMassawaUTMZone37N() {
    return MassawaUTMZone37N.copy();
  }

  /**
   * @return the MhastUTMZone32S
   */
  public ProjectionInfo getMhastUTMZone32S() {
    return MhastUTMZone32S.copy();
  }

  /**
   * @return the MinnaUTMZone31N
   */
  public ProjectionInfo getMinnaUTMZone31N() {
    return MinnaUTMZone31N.copy();
  }

  /**
   * @return the MinnaUTMZone32N
   */
  public ProjectionInfo getMinnaUTMZone32N() {
    return MinnaUTMZone32N.copy();
  }

  /**
   * @return the MoznetUTMZone36S
   */
  public ProjectionInfo getMoznetUTMZone36S() {
    return MoznetUTMZone36S.copy();
  }

  /**
   * @return the MoznetUTMZone37S
   */
  public ProjectionInfo getMoznetUTMZone37S() {
    return MoznetUTMZone37S.copy();
  }

  /**
   * @return the MporalokoUTMZone32N
   */
  public ProjectionInfo getMporalokoUTMZone32N() {
    return MporalokoUTMZone32N.copy();
  }

  /**
   * @return the MporalokoUTMZone32S
   */
  public ProjectionInfo getMporalokoUTMZone32S() {
    return MporalokoUTMZone32S.copy();
  }

  /**
   * @return the NAD1927BLMZone14N
   */
  public ProjectionInfo getNAD1927BLMZone14N() {
    return NAD1927BLMZone14N.copy();
  }

  /**
   * @return the NAD1927BLMZone15N
   */
  public ProjectionInfo getNAD1927BLMZone15N() {
    return NAD1927BLMZone15N.copy();
  }

  /**
   * @return the NAD1927BLMZone16N
   */
  public ProjectionInfo getNAD1927BLMZone16N() {
    return NAD1927BLMZone16N.copy();
  }

  /**
   * @return the NAD1927BLMZone17N
   */
  public ProjectionInfo getNAD1927BLMZone17N() {
    return NAD1927BLMZone17N.copy();
  }

  /**
   * @return the NAD1983HARNUTMZone11N
   */
  public ProjectionInfo getNAD1983HARNUTMZone11N() {
    return NAD1983HARNUTMZone11N.copy();
  }

  /**
   * @return the NAD1983HARNUTMZone12N
   */
  public ProjectionInfo getNAD1983HARNUTMZone12N() {
    return NAD1983HARNUTMZone12N.copy();
  }

  /**
   * @return the NAD1983HARNUTMZone13N
   */
  public ProjectionInfo getNAD1983HARNUTMZone13N() {
    return NAD1983HARNUTMZone13N.copy();
  }

  /**
   * @return the NAD1983HARNUTMZone18N
   */
  public ProjectionInfo getNAD1983HARNUTMZone18N() {
    return NAD1983HARNUTMZone18N.copy();
  }

  /**
   * @return the NAD1983HARNUTMZone2S
   */
  public ProjectionInfo getNAD1983HARNUTMZone2S() {
    return NAD1983HARNUTMZone2S.copy();
  }

  /**
   * @return the NAD1983HARNUTMZone4N
   */
  public ProjectionInfo getNAD1983HARNUTMZone4N() {
    return NAD1983HARNUTMZone4N.copy();
  }

  /**
   * @return the NAD1983HARNUTMZone5N
   */
  public ProjectionInfo getNAD1983HARNUTMZone5N() {
    return NAD1983HARNUTMZone5N.copy();
  }

  /**
   * @return the NEA74NoumeaUTMZone58S
   */
  public ProjectionInfo getNEA74NoumeaUTMZone58S() {
    return NEA74NoumeaUTMZone58S.copy();
  }

  /**
   * @return the NGNUTMZone38N
   */
  public ProjectionInfo getNGNUTMZone38N() {
    return NGNUTMZone38N.copy();
  }

  /**
   * @return the NGNUTMZone39N
   */
  public ProjectionInfo getNGNUTMZone39N() {
    return NGNUTMZone39N.copy();
  }

  /**
   * @return the NGO1948UTMZone32N
   */
  public ProjectionInfo getNGO1948UTMZone32N() {
    return NGO1948UTMZone32N.copy();
  }

  /**
   * @return the NGO1948UTMZone33N
   */
  public ProjectionInfo getNGO1948UTMZone33N() {
    return NGO1948UTMZone33N.copy();
  }

  /**
   * @return the NGO1948UTMZone34N
   */
  public ProjectionInfo getNGO1948UTMZone34N() {
    return NGO1948UTMZone34N.copy();
  }

  /**
   * @return the NGO1948UTMZone35N
   */
  public ProjectionInfo getNGO1948UTMZone35N() {
    return NGO1948UTMZone35N.copy();
  }

  /**
   * @return the NZGD1949UTMZone58S
   */
  public ProjectionInfo getNZGD1949UTMZone58S() {
    return NZGD1949UTMZone58S.copy();
  }

  /**
   * @return the NZGD1949UTMZone59S
   */
  public ProjectionInfo getNZGD1949UTMZone59S() {
    return NZGD1949UTMZone59S.copy();
  }

  /**
   * @return the NZGD1949UTMZone60S
   */
  public ProjectionInfo getNZGD1949UTMZone60S() {
    return NZGD1949UTMZone60S.copy();
  }

  /**
   * @return the NZGD2000UTMZone58S
   */
  public ProjectionInfo getNZGD2000UTMZone58S() {
    return NZGD2000UTMZone58S.copy();
  }

  /**
   * @return the NZGD2000UTMZone59S
   */
  public ProjectionInfo getNZGD2000UTMZone59S() {
    return NZGD2000UTMZone59S.copy();
  }

  /**
   * @return the NZGD2000UTMZone60S
   */
  public ProjectionInfo getNZGD2000UTMZone60S() {
    return NZGD2000UTMZone60S.copy();
  }

  /**
   * @return the Nahrwan1967UTMZone38N
   */
  public ProjectionInfo getNahrwan1967UTMZone38N() {
    return Nahrwan1967UTMZone38N.copy();
  }

  /**
   * @return the Nahrwan1967UTMZone39N
   */
  public ProjectionInfo getNahrwan1967UTMZone39N() {
    return Nahrwan1967UTMZone39N.copy();
  }

  /**
   * @return the Nahrwan1967UTMZone40N
   */
  public ProjectionInfo getNahrwan1967UTMZone40N() {
    return Nahrwan1967UTMZone40N.copy();
  }

  /**
   * @return the Naparima1955UTMZone20N
   */
  public ProjectionInfo getNaparima1955UTMZone20N() {
    return Naparima1955UTMZone20N.copy();
  }

  /**
   * @return the Naparima1972UTMZone20N
   */
  public ProjectionInfo getNaparima1972UTMZone20N() {
    return Naparima1972UTMZone20N.copy();
  }

  /**
   * @return the NordSahara1959UTMZone29N
   */
  public ProjectionInfo getNordSahara1959UTMZone29N() {
    return NordSahara1959UTMZone29N.copy();
  }

  /**
   * @return the NordSahara1959UTMZone30N
   */
  public ProjectionInfo getNordSahara1959UTMZone30N() {
    return NordSahara1959UTMZone30N.copy();
  }

  /**
   * @return the NordSahara1959UTMZone31N
   */
  public ProjectionInfo getNordSahara1959UTMZone31N() {
    return NordSahara1959UTMZone31N.copy();
  }

  /**
   * @return the NordSahara1959UTMZone32N
   */
  public ProjectionInfo getNordSahara1959UTMZone32N() {
    return NordSahara1959UTMZone32N.copy();
  }

  /**
   * @return the ObservMeteorologico1939UTMZone25N
   */
  public ProjectionInfo getObservMeteorologico1939UTMZone25N() {
    return ObservMeteorologico1939UTMZone25N.copy();
  }

  /**
   * @return the OldHawaiianUTMZone4N
   */
  public ProjectionInfo getOldHawaiianUTMZone4N() {
    return OldHawaiianUTMZone4N.copy();
  }

  /**
   * @return the OldHawaiianUTMZone5N
   */
  public ProjectionInfo getOldHawaiianUTMZone5N() {
    return OldHawaiianUTMZone5N.copy();
  }

  /**
   * @return the PDO1993UTMZone39N
   */
  public ProjectionInfo getPDO1993UTMZone39N() {
    return PDO1993UTMZone39N.copy();
  }

  /**
   * @return the PDO1993UTMZone40N
   */
  public ProjectionInfo getPDO1993UTMZone40N() {
    return PDO1993UTMZone40N.copy();
  }

  /**
   * @return the PointeNoireUTMZone32S
   */
  public ProjectionInfo getPointeNoireUTMZone32S() {
    return PointeNoireUTMZone32S.copy();
  }

  /**
   * @return the PortoSanto1936UTMZone28N
   */
  public ProjectionInfo getPortoSanto1936UTMZone28N() {
    return PortoSanto1936UTMZone28N.copy();
  }

  /**
   * @return the PortoSanto1995UTMZone28N
   */
  public ProjectionInfo getPortoSanto1995UTMZone28N() {
    return PortoSanto1995UTMZone28N.copy();
  }

  /**
   * @return the ProvSAmerDatumUTMZone17s
   */
  public ProjectionInfo getProvSAmerDatumUTMZone17s() {
    return ProvSAmerDatumUTMZone17s.copy();
  }

  /**
   * @return the ProvSAmerDatumUTMZone18N
   */
  public ProjectionInfo getProvSAmerDatumUTMZone18N() {
    return ProvSAmerDatumUTMZone18N.copy();
  }

  /**
   * @return the ProvSAmerDatumUTMZone18S
   */
  public ProjectionInfo getProvSAmerDatumUTMZone18S() {
    return ProvSAmerDatumUTMZone18S.copy();
  }

  /**
   * @return the ProvSAmerDatumUTMZone19N
   */
  public ProjectionInfo getProvSAmerDatumUTMZone19N() {
    return ProvSAmerDatumUTMZone19N.copy();
  }

  /**
   * @return the ProvSAmerDatumUTMZone19S
   */
  public ProjectionInfo getProvSAmerDatumUTMZone19S() {
    return ProvSAmerDatumUTMZone19S.copy();
  }

  /**
   * @return the ProvSAmerDatumUTMZone20N
   */
  public ProjectionInfo getProvSAmerDatumUTMZone20N() {
    return ProvSAmerDatumUTMZone20N.copy();
  }

  /**
   * @return the ProvSAmerDatumUTMZone20S
   */
  public ProjectionInfo getProvSAmerDatumUTMZone20S() {
    return ProvSAmerDatumUTMZone20S.copy();
  }

  /**
   * @return the ProvSAmerDatumUTMZone21N
   */
  public ProjectionInfo getProvSAmerDatumUTMZone21N() {
    return ProvSAmerDatumUTMZone21N.copy();
  }

  /**
   * @return the ProvSAmerDatumUTMZone22S
   */
  public ProjectionInfo getProvSAmerDatumUTMZone22S() {
    return ProvSAmerDatumUTMZone22S.copy();
  }

  /**
   * @return the PuertoRicoUTMZone20N
   */
  public ProjectionInfo getPuertoRicoUTMZone20N() {
    return PuertoRicoUTMZone20N.copy();
  }

  /**
   * @return the Qornoq1927UTMZone22N
   */
  public ProjectionInfo getQornoq1927UTMZone22N() {
    return Qornoq1927UTMZone22N.copy();
  }

  /**
   * @return the Qornoq1927UTMZone23N
   */
  public ProjectionInfo getQornoq1927UTMZone23N() {
    return Qornoq1927UTMZone23N.copy();
  }

  /**
   * @return the REGVENUTMZone18N
   */
  public ProjectionInfo getREGVENUTMZone18N() {
    return REGVENUTMZone18N.copy();
  }

  /**
   * @return the REGVENUTMZone19N
   */
  public ProjectionInfo getREGVENUTMZone19N() {
    return REGVENUTMZone19N.copy();
  }

  /**
   * @return the REGVENUTMZone20N
   */
  public ProjectionInfo getREGVENUTMZone20N() {
    return REGVENUTMZone20N.copy();
  }

  /**
   * @return the RGFG1995UTMZone22N
   */
  public ProjectionInfo getRGFG1995UTMZone22N() {
    return RGFG1995UTMZone22N.copy();
  }

  /**
   * @return the RGR1992UTMZone40S
   */
  public ProjectionInfo getRGR1992UTMZone40S() {
    return RGR1992UTMZone40S.copy();
  }

  /**
   * @return the RRAF1991UTMZone20N
   */
  public ProjectionInfo getRRAF1991UTMZone20N() {
    return RRAF1991UTMZone20N.copy();
  }

  /**
   * @return the SIRGASUTMZone17N
   */
  public ProjectionInfo getSIRGASUTMZone17N() {
    return SIRGASUTMZone17N.copy();
  }

  /**
   * @return the SIRGASUTMZone17S
   */
  public ProjectionInfo getSIRGASUTMZone17S() {
    return SIRGASUTMZone17S.copy();
  }

  /**
   * @return the SIRGASUTMZone18N
   */
  public ProjectionInfo getSIRGASUTMZone18N() {
    return SIRGASUTMZone18N.copy();
  }

  /**
   * @return the SIRGASUTMZone18S
   */
  public ProjectionInfo getSIRGASUTMZone18S() {
    return SIRGASUTMZone18S.copy();
  }

  /**
   * @return the SIRGASUTMZone19N
   */
  public ProjectionInfo getSIRGASUTMZone19N() {
    return SIRGASUTMZone19N.copy();
  }

  /**
   * @return the SIRGASUTMZone19S
   */
  public ProjectionInfo getSIRGASUTMZone19S() {
    return SIRGASUTMZone19S.copy();
  }

  /**
   * @return the SIRGASUTMZone20N
   */
  public ProjectionInfo getSIRGASUTMZone20N() {
    return SIRGASUTMZone20N.copy();
  }

  /**
   * @return the SIRGASUTMZone20S
   */
  public ProjectionInfo getSIRGASUTMZone20S() {
    return SIRGASUTMZone20S.copy();
  }

  /**
   * @return the SIRGASUTMZone21N
   */
  public ProjectionInfo getSIRGASUTMZone21N() {
    return SIRGASUTMZone21N.copy();
  }

  /**
   * @return the SIRGASUTMZone21S
   */
  public ProjectionInfo getSIRGASUTMZone21S() {
    return SIRGASUTMZone21S.copy();
  }

  /**
   * @return the SIRGASUTMZone22N
   */
  public ProjectionInfo getSIRGASUTMZone22N() {
    return SIRGASUTMZone22N.copy();
  }

  /**
   * @return the SIRGASUTMZone22S
   */
  public ProjectionInfo getSIRGASUTMZone22S() {
    return SIRGASUTMZone22S.copy();
  }

  /**
   * @return the SIRGASUTMZone23S
   */
  public ProjectionInfo getSIRGASUTMZone23S() {
    return SIRGASUTMZone23S.copy();
  }

  /**
   * @return the SIRGASUTMZone24S
   */
  public ProjectionInfo getSIRGASUTMZone24S() {
    return SIRGASUTMZone24S.copy();
  }

  /**
   * @return the SIRGASUTMZone25S
   */
  public ProjectionInfo getSIRGASUTMZone25S() {
    return SIRGASUTMZone25S.copy();
  }

  /**
   * @return the ST71BelepUTMZone58S
   */
  public ProjectionInfo getST71BelepUTMZone58S() {
    return ST71BelepUTMZone58S.copy();
  }

  /**
   * @return the ST84IledesPinsUTMZone58S
   */
  public ProjectionInfo getST84IledesPinsUTMZone58S() {
    return ST84IledesPinsUTMZone58S.copy();
  }

  /**
   * @return the ST87OuveaUTMZone58S
   */
  public ProjectionInfo getST87OuveaUTMZone58S() {
    return ST87OuveaUTMZone58S.copy();
  }

  /**
   * @return the SaintPierreetMiquelon1950UTMZone21N
   */
  public ProjectionInfo getSaintPierreetMiquelon1950UTMZone21N() {
    return SaintPierreetMiquelon1950UTMZone21N.copy();
  }

  /**
   * @return the SainteAnneUTMZone20N
   */
  public ProjectionInfo getSainteAnneUTMZone20N() {
    return SainteAnneUTMZone20N.copy();
  }

  /**
   * @return the SambojaUTMZone50S
   */
  public ProjectionInfo getSambojaUTMZone50S() {
    return SambojaUTMZone50S.copy();
  }

  /**
   * @return the SaoBrazUTMZone26N
   */
  public ProjectionInfo getSaoBrazUTMZone26N() {
    return SaoBrazUTMZone26N.copy();
  }

  /**
   * @return the SapperHill1943UTMZone20S
   */
  public ProjectionInfo getSapperHill1943UTMZone20S() {
    return SapperHill1943UTMZone20S.copy();
  }

  /**
   * @return the SapperHill1943UTMZone21S
   */
  public ProjectionInfo getSapperHill1943UTMZone21S() {
    return SapperHill1943UTMZone21S.copy();
  }

  /**
   * @return the SchwarzeckUTMZone33S
   */
  public ProjectionInfo getSchwarzeckUTMZone33S() {
    return SchwarzeckUTMZone33S.copy();
  }

  /**
   * @return the SelvagemGrande1938UTMZone28N
   */
  public ProjectionInfo getSelvagemGrande1938UTMZone28N() {
    return SelvagemGrande1938UTMZone28N.copy();
  }

  /**
   * @return the SierraLeone1968UTMZone28N
   */
  public ProjectionInfo getSierraLeone1968UTMZone28N() {
    return SierraLeone1968UTMZone28N.copy();
  }

  /**
   * @return the SierraLeone1968UTMZone29N
   */
  public ProjectionInfo getSierraLeone1968UTMZone29N() {
    return SierraLeone1968UTMZone29N.copy();
  }

  /**
   * @return the SouthAmerican1969UTMZone17S
   */
  public ProjectionInfo getSouthAmerican1969UTMZone17S() {
    return SouthAmerican1969UTMZone17S.copy();
  }

  /**
   * @return the SouthAmerican1969UTMZone18N
   */
  public ProjectionInfo getSouthAmerican1969UTMZone18N() {
    return SouthAmerican1969UTMZone18N.copy();
  }

  /**
   * @return the SouthAmerican1969UTMZone18S
   */
  public ProjectionInfo getSouthAmerican1969UTMZone18S() {
    return SouthAmerican1969UTMZone18S.copy();
  }

  /**
   * @return the SouthAmerican1969UTMZone19N
   */
  public ProjectionInfo getSouthAmerican1969UTMZone19N() {
    return SouthAmerican1969UTMZone19N.copy();
  }

  /**
   * @return the SouthAmerican1969UTMZone19S
   */
  public ProjectionInfo getSouthAmerican1969UTMZone19S() {
    return SouthAmerican1969UTMZone19S.copy();
  }

  /**
   * @return the SouthAmerican1969UTMZone20N
   */
  public ProjectionInfo getSouthAmerican1969UTMZone20N() {
    return SouthAmerican1969UTMZone20N.copy();
  }

  /**
   * @return the SouthAmerican1969UTMZone20S
   */
  public ProjectionInfo getSouthAmerican1969UTMZone20S() {
    return SouthAmerican1969UTMZone20S.copy();
  }

  /**
   * @return the SouthAmerican1969UTMZone21N
   */
  public ProjectionInfo getSouthAmerican1969UTMZone21N() {
    return SouthAmerican1969UTMZone21N.copy();
  }

  /**
   * @return the SouthAmerican1969UTMZone21S
   */
  public ProjectionInfo getSouthAmerican1969UTMZone21S() {
    return SouthAmerican1969UTMZone21S.copy();
  }

  /**
   * @return the SouthAmerican1969UTMZone22N
   */
  public ProjectionInfo getSouthAmerican1969UTMZone22N() {
    return SouthAmerican1969UTMZone22N.copy();
  }

  /**
   * @return the SouthAmerican1969UTMZone22S
   */
  public ProjectionInfo getSouthAmerican1969UTMZone22S() {
    return SouthAmerican1969UTMZone22S.copy();
  }

  /**
   * @return the SouthAmerican1969UTMZone23S
   */
  public ProjectionInfo getSouthAmerican1969UTMZone23S() {
    return SouthAmerican1969UTMZone23S.copy();
  }

  /**
   * @return the SouthAmerican1969UTMZone24S
   */
  public ProjectionInfo getSouthAmerican1969UTMZone24S() {
    return SouthAmerican1969UTMZone24S.copy();
  }

  /**
   * @return the SouthAmerican1969UTMZone25S
   */
  public ProjectionInfo getSouthAmerican1969UTMZone25S() {
    return SouthAmerican1969UTMZone25S.copy();
  }

  /**
   * @return the SudanUTMZone35N
   */
  public ProjectionInfo getSudanUTMZone35N() {
    return SudanUTMZone35N.copy();
  }

  /**
   * @return the SudanUTMZone36N
   */
  public ProjectionInfo getSudanUTMZone36N() {
    return SudanUTMZone36N.copy();
  }

  /**
   * @return the TahaaUTMZone5S
   */
  public ProjectionInfo getTahaaUTMZone5S() {
    return TahaaUTMZone5S.copy();
  }

  /**
   * @return the TahitiUTMZone6S
   */
  public ProjectionInfo getTahitiUTMZone6S() {
    return TahitiUTMZone6S.copy();
  }

  /**
   * @return the Tananarive1925UTMZone38S
   */
  public ProjectionInfo getTananarive1925UTMZone38S() {
    return Tananarive1925UTMZone38S.copy();
  }

  /**
   * @return the Tananarive1925UTMZone39S
   */
  public ProjectionInfo getTananarive1925UTMZone39S() {
    return Tananarive1925UTMZone39S.copy();
  }

  /**
   * @return the TeteUTMZone36S
   */
  public ProjectionInfo getTeteUTMZone36S() {
    return TeteUTMZone36S.copy();
  }

  /**
   * @return the TeteUTMZone37S
   */
  public ProjectionInfo getTeteUTMZone37S() {
    return TeteUTMZone37S.copy();
  }

  /**
   * @return the Timbalai1948UTMZone49N
   */
  public ProjectionInfo getTimbalai1948UTMZone49N() {
    return Timbalai1948UTMZone49N.copy();
  }

  /**
   * @return the Timbalai1948UTMZone50N
   */
  public ProjectionInfo getTimbalai1948UTMZone50N() {
    return Timbalai1948UTMZone50N.copy();
  }

  /**
   * @return the TokyoUTMZone51N
   */
  public ProjectionInfo getTokyoUTMZone51N() {
    return TokyoUTMZone51N.copy();
  }

  /**
   * @return the TokyoUTMZone52N
   */
  public ProjectionInfo getTokyoUTMZone52N() {
    return TokyoUTMZone52N.copy();
  }

  /**
   * @return the TokyoUTMZone53N
   */
  public ProjectionInfo getTokyoUTMZone53N() {
    return TokyoUTMZone53N.copy();
  }

  /**
   * @return the TokyoUTMZone54N
   */
  public ProjectionInfo getTokyoUTMZone54N() {
    return TokyoUTMZone54N.copy();
  }

  /**
   * @return the TokyoUTMZone55N
   */
  public ProjectionInfo getTokyoUTMZone55N() {
    return TokyoUTMZone55N.copy();
  }

  /**
   * @return the TokyoUTMZone56N
   */
  public ProjectionInfo getTokyoUTMZone56N() {
    return TokyoUTMZone56N.copy();
  }

  /**
   * @return the TrucialCoast1948UTMZone39N
   */
  public ProjectionInfo getTrucialCoast1948UTMZone39N() {
    return TrucialCoast1948UTMZone39N.copy();
  }

  /**
   * @return the TrucialCoast1948UTMZone40N
   */
  public ProjectionInfo getTrucialCoast1948UTMZone40N() {
    return TrucialCoast1948UTMZone40N.copy();
  }

  /**
   * @return the YemenNGN1996UTMZone38N
   */
  public ProjectionInfo getYemenNGN1996UTMZone38N() {
    return YemenNGN1996UTMZone38N.copy();
  }

  /**
   * @return the YemenNGN1996UTMZone39N
   */
  public ProjectionInfo getYemenNGN1996UTMZone39N() {
    return YemenNGN1996UTMZone39N.copy();
  }

  /**
   * @return the Yoff1972UTMZone28N
   */
  public ProjectionInfo getYoff1972UTMZone28N() {
    return Yoff1972UTMZone28N.copy();
  }

  /**
   * @return the Zanderij1972UTMZone21N
   */
  public ProjectionInfo getZanderij1972UTMZone21N() {
    return Zanderij1972UTMZone21N.copy();
  }
}
