# Prueba Técnica - API Rest

Este proyecto es una API Rest para la gestión de torneos y partidos.

## Ejecución de la API Rest

### 1. Clonar el repositorio

2. Configuración en IntelliJ
Recomiendo usar IntelliJ. Al abrir el proyecto con IntelliJ, antes de hacer el primer build, sigue estos pasos:

Ve a File | Settings | Build, Execution, Deployment | Compiler | Annotation Processors.
Haz clic debajo de Annotation profile for pruebaTecnica en pruebaTecnica.
Asegúrate de que esté activado el Enable annotations processing.
Asegúrate de que esté marcada la opción Obtain processors from project classpath, ya que sii está marcada la opción de processor path, al menos en mi caso, Lombok no funciona correctamente.
3. Hacer Build en el proyecto
Realiza el build del proyecto en IntelliJ.

4. Ejecutar el aplicativo
Ejecuta la aplicación desde IntelliJ.

5. Pruebas con Postman
Finalmente, ya se pueden hacer pruebas desde Postman con los mismos endpoints dados en la guía de la prueba.

Ejemplo de body para crear torneo

{
  "name": "Champions League",
  "sport": "FOOTBALL",
  "startDate": "2024-06-01T00:00:00Z",
  "endDate": "2024-07-01T00:00:00Z",
  "teams": [
    { "id": "team1", "name": "Real Madrid" },
    { "id": "team2", "name": "Barcelona" },
    { "id": "team3", "name": "Manchester City" },
    { "id": "team4", "name": "Bayern Munich" }
  ]
}


![image](https://github.com/user-attachments/assets/0f1ce405-7185-4b9e-b638-8501e37753fa)


Ejemplo de body para crear partido

{
  "homeTeam": { "teamId": "team1", "score": 0 },
  "awayTeam": { "teamId": "team2", "score": 0 },
  "date": "2024-06-05T20:00:00Z",
  "tournamentId": "{id_torneo_generado}"
}

![image](https://github.com/user-attachments/assets/33739f7f-f0f9-4da4-b530-e4eeb9a1a245)


Ejemplo de body para actualizar resultados

![image](https://github.com/user-attachments/assets/c3c739c1-fc40-409e-8f70-49429522b240)

{
  "homeScore": 2,
  "awayScore": 1
}
