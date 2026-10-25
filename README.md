# PHP2Shell — CVE-2026-109618 y CVE-2026-109619

Informe educativo interactivo sobre dos vulnerabilidades críticas de seguridad de memoria en el núcleo de PHP, sus versiones afectadas, correcciones y comprobaciones para Twinteach (Angular en frontend y Laravel/PHP en backend).

## Frontend

Requiere Node.js. Desde esta carpeta:

```cmd
npm install
npm run dev
```

Abre la URL local que imprime Vite (normalmente http://localhost:5173).

## Backend opcional

La API Spring Boot evalúa versiones hipotéticas de PHP; no escanea hosts. Requiere Java 17 y Maven:

```cmd
cd backend
mvn spring-boot:run
```

El servicio escucha en el puerto 8080.

## Fuentes principales

- PHP: https://github.com/php/php-src/security/advisories/GHSA-w8hw-w9fx-j79w
- PHP: https://github.com/php/php-src/security/advisories/GHSA-6wmf-4rv5-p3rp
- Wiz Research: https://www.wiz.io/blog/php2shell-php-rce
