package prat_eng_soft_api_test_automatizado.utils;

import java.io.File;

public class ContratoManager {

    private static final String DIR_PATH_PROPERTIES = System.getProperty("user.dir") + File.separator + "src"
            + File.separator +
            "test" + File.separator + "resources" + File.separator + "contratos" + File.separator;

    private static File getFile(String fileName) {

        try {
            File file = new File(DIR_PATH_PROPERTIES + fileName + ".json");
            return file;
        } catch (Exception e) {
            System.out.println("Não foi possível carregar o arquivo" + e.getMessage());
        }

        return null;
    }

    public static File getContrato(String fileName) {
        return getFile(fileName);
    }
}
