
package si.fri.rso.katalogskupin;

import java.util.ArrayList;
import java.util.List;


public class Database {
    private static List<Skupina> skupinas = new ArrayList<>();

    public static List<Skupina> getSkupinas() {
        /*Skupina cus = new Skupina();
        cus.setId("1");
        cus.setName("FRI MAG letnik 2016/2017");
        cus.setAbout("Skupina za študente ki so se v drugo stopnjo vpisali navedenega leta.");
        skupinas.add(cus);*/

        return skupinas;
    }

    public static Skupina getSkupina(String skupinaId) {
        for (Skupina skupina : skupinas) {
            if (skupina.getId().equals(skupinaId))
                return skupina;
        }

        return null;
    }

    public static void addSkupina(Skupina skupina) {
        skupinas.add(skupina);
    }

    public static void deleteSkupina(String skupinaId) {
        for (Skupina skupina : skupinas) {
            if (skupina.getId().equals(skupinaId)) {
                skupinas.remove(skupina);
                break;
            }
        }
    }
}
