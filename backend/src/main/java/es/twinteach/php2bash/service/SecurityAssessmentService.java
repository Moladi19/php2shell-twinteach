package es.twinteach.php2bash.service;

import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class SecurityAssessmentService {
    private static final Map<String, String> PATCHES = Map.of(
        "8.2", "8.2.35",
        "8.3", "8.3.36",
        "8.4", "8.4.27",
        "8.5", "8.5.12"
    );

    public Assessment assess(String version, String runtime) {
        if (version == null || version.isBlank() || runtime == null || runtime.isBlank()) {
            throw new IllegalArgumentException("version y runtime son obligatorios");
        }
        String family = version.length() >= 3 ? version.substring(0, 3) : "";
        String fixedVersion = PATCHES.get(family);
        if (fixedVersion == null) {
            return new Assessment("warning", "Confirma la versión con tu proveedor",
                "PHP 8.1 y anteriores también pueden estar afectados. Comprueba si existe un backport oficial; si no, migra a una rama corregida.");
        }
        if (compare(version, fixedVersion) >= 0) {
            return new Assessment("success", "Alcanza el primer parche oficial de su rama",
                "Confirma que todos los procesos utilizan este binario y que tu proveedor no requiere pasos adicionales.");
        }
        return new Assessment("danger", "Anterior al parche oficial de su rama",
            "Esta versión es anterior a " + fixedVersion + ". Actualiza y revisa posibles indicios; confirma también cualquier backport del proveedor.");
    }

    private int compare(String left, String right) {
        String[] a = left.split("\\."), b = right.split("\\.");
        for (int i = 0; i < Math.max(a.length, b.length); i++) {
            int av = i < a.length ? Integer.parseInt(a[i]) : 0;
            int bv = i < b.length ? Integer.parseInt(b[i]) : 0;
            if (av != bv) return Integer.compare(av, bv);
        }
        return 0;
    }

    public record Assessment(String tone, String title, String body) { }
}
