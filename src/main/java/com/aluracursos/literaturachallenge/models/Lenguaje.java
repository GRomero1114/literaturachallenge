package com.aluracursos.literaturachallenge.models;

public enum Lenguaje {
    AFAR ("aa"),
    ABJASIO ("ab"),
    AVESTICO ("ae"),
    AFRIKAANS ("af"),
    AKANO ("ak"),
    AMHARICO ("am"),
    ARAGONES ("an"),
    ARABE ("ar"),
    ASAMES ("as"),
    AVAR ("av"),
    AIMARA ("ay"),
    AZERI ("az"),
    BASKIR ("ba"),
    BIELORRUSO ("be"),
    BULGARO ("bg"),
    BHOYAPURI ("bh"),
    BISLAMA ("bi"),
    BAMBARA ("bm"),
    BENGALI ("bn"),
    TIBETANO ("bo"),
    BRETON ("br"),
    BOSNIO ("bs"),
    CATALAN ("ca"),
    CHECHENO ("ce"),
    CHAMORRO ("ch"),
    CORSO ("co"),
    CREE ("cr"),
    CHECO ("cs"),
    ESLAVO_ECLESIASTICO_ANTIGUO ("cu"),
    CHUVASIO ("cv"),
    GALES ("cy"),
    DANES ("da"),
    ALEMAN ("de"),
    MALDIVO ("dv"),
    DZONGKHA ("dz"),
    EWE ("ee"),
    GRIEGO ("el"),
    INGLES ("en"),
    ESPERANTO ("eo"),
    ESPANOL ("es"),
    ESTONIO ("et"),
    EUSKERA ("eu"),
    PERSA ("fa"),
    FULA ("ff"),
    FINES ("fi"),
    FIYIANO ("fj"),
    FEROES ("fo"),
    FRANCES ("fr"),
    FRISON ("fy"),
    IRLANDES ("ga"),
    GAELICO_ESCOCES ("gd"),
    GALLEGO ("gl"),
    GUARANI ("gn"),
    GUYARATI ("gu"),
    MANES ("gv"),
    HAUSA ("ha"),
    HEBREO ("he"),
    HINDI ("hi"),
    HIRI_MOTU ("ho"),
    CROATA ("hr"),
    HAITIANO ("ht"),
    HUNGARO ("hu"),
    ARMENIO ("hy"),
    HERERO ("hz"),
    INTERLINGUA ("ia"),
    INDONESIO ("id"),
    OCCIDENTAL ("ie"),
    IGBO ("ig"),
    YI_DE_SICHUAN ("ii"),
    INUPIAQ ("ik"),
    IDO ("io"),
    ISLANDES ("is"),
    ITALIANO ("it"),
    INUKTITUT ("iu"),
    JAPONES ("ja"),
    JAVANES ("jv"),
    GEORGIANO ("ka"),
    KONGO ("kg"),
    KIKUYU ("ki"),
    KUANYAMA ("kj"),
    KAZAJO ("kk"),
    GROENLANDES ("kl"),
    CAMBOYANO ("km"),
    CANARES ("kn"),
    COREANO ("ko"),
    KANURI ("kr"),
    CACHEMIRO ("ks"),
    KURDO ("ku"),
    KOMI ("kv"),
    CORNICO ("kw"),
    KIRGUIS ("ky"),
    LATIN ("la"),
    LUXEMBURGUES ("lb"),
    LUGANDA ("lg"),
    LIMBURGUES ("li"),
    LINGALA ("ln"),
    LAO ("lo"),
    LITUANO ("lt"),
    LUBA_KATANGA ("lu"),
    LETON ("lv"),
    MALGACHE ("mg"),
    MARSHALES ("mh"),
    MAORI ("mi"),
    MACEDONIO ("mk"),
    MALAYALAM ("ml"),
    MONGOL ("mn"),
    MARATI ("mr"),
    MALAYO ("ms"),
    MALTES ("mt"),
    BIRMANO ("my"),
    NAURUANO ("na"),
    NORUEGO_BOKMAL ("nb"),
    NDEBELE_DEL_NORTE ("nd"),
    NEPALI ("ne"),
    NDONGA ("ng"),
    NEERLANDES ("nl"),
    NYNORSK ("nn"),
    NORUEGO ("no"),
    NDEBELE_DEL_SUR ("nr"),
    NAVAJO ("nv"),
    CHICHEWA ("ny"),
    OCCITANO ("oc"),
    OJIBWA ("oj"),
    OROMO ("om"),
    ORIYA ("or"),
    OSETICO ("os"),
    PANYABI ("pa"),
    PALI ("pi"),
    POLACO ("pl"),
    PASTU ("ps"),
    PORTUGUES ("pt"),
    QUECHUA ("qc"),
    ROMANCHE ("rm"),
    KIRUNDI ("rn"),
    RUMANO ("ro"),
    RUSO ("ru"),
    RUANDES ("rw"),
    SANSCRITO ("sa"),
    SARDO ("sc"),
    SINDHI ("sd"),
    SAMI_SEPTENTRIONAL ("se"),
    SANGO ("sg"),
    CINGALES ("si"),
    ESLOVACO ("sk"),
    ESLOVENO ("sl"),
    SAMOANO ("sm"),
    SHONA ("sn"),
    SOMALI ("so"),
    ALBANES ("sq"),
    SERBIO ("sr"),
    SUAZI ("ss"),
    SESOTHO ("st"),
    SUNDANES ("su"),
    SUECO ("sv"),
    SUAJILI ("sw"),
    TAMIL ("ta"),
    TELUGU ("te"),
    TAYIKO ("tg"),
    TAILANDES ("th"),
    TIGRINA ("ti"),
    TURCOMANO ("tk"),
    TAGALO ("tl"),
    SETSUANA ("tn"),
    TONGANO ("to"),
    TURCO ("tr"),
    TSONGA ("ts"),
    TARTARO ("tt"),
    TWI ("tw"),
    TAHITIANO ("ty"),
    UIGUR ("ug"),
    UCRANIANO ("uk"),
    URDU ("ur"),
    UZBEKO ("uz"),
    VENDA ("ve"),
    VIETNAMITA ("vi"),
    VOLAPUK ("vo"),
    VALON ("wa"),
    WOLOF ("wo"),
    XHOSA ("xh"),
    YIDISH ("yi"),
    YORUBA ("yo"),
    CHUAN ("za"),
    CHINO ("zh"),
    ZULU ("zu"),
    NO_INFORMATION ("zz");



    private String lenguajesBase;

    Lenguaje (String lenguajesBase){
        this.lenguajesBase=lenguajesBase;
    }

    public static Lenguaje fromString(String text) {
        for (Lenguaje lenguaje : Lenguaje.values()) {
            if (lenguaje.lenguajesBase.equalsIgnoreCase(text)) {
                return lenguaje;
            }
        }
        return NO_INFORMATION;
        //throw new IllegalArgumentException("Ningun lenguaje encontrado: " + text);
    }

/*
public enum Lenguaje {
    ESPANOL("es"),
    FRANCES("fr"),
    INGLES("en"),
    PORTUGES("pt"),
    DESCONOCIDA(Desco)

    private String lenguajesBase;

    Lenguaje (String lenguajesBase){
        this.lenguajesBase=lenguajesBase;
    }

    public static Lenguaje fromString(String text) {
        for (Lenguaje categoria : Lenguaje.values()) {
            if (categoria.lenguajesBase.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Ningun lenguaje encontrado: " + text);
    }


    }
*/
}



