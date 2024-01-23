
# Mihaicuta Iulia - 334CA

## Planificarea de task-uri într-un datacenter

Acest proiect implementează un sistem de dispatching pentru distribuirea sarcinilor (task-uri) către diferite host-uri, utilizând patru algoritmi diferiți. Sistemul este compus din două clase principale: `MyHost` și `MyDispatcher`.

### `MyHost.java`
Clasa `MyHost` reprezintă un host individual în sistem. Aceasta gestionează o coadă de priorități pentru sarcinile alocate și execută următoarele funcții:
- **Gestionarea Cozii:** Utilizează o coadă de priorități pentru a ordona sarcinile bazat pe prioritate și timpul de start.
- **Execuția Sarcinilor:** Rulează și finalizează sarcinile, având capacitatea de a opri execuția sarcinilor preemtibile.
- **Funcționare:** Așteaptă primirea de sarcini și le adaugă în coada de priorități, atât timp cât nu a primit un mesaj de oprire a execuției.

### `MyDispatcher.java`
Clasa `MyDispatcher` este responsabilă pentru distribuirea sarcinilor către host-uri folosind unul dintre următorii algoritmi:
- **Round Robin (RR):** 
    - `private void addTaskRR(Task task)` - Adaugă sarcina `task` în coada de priorități a host-ului cu indexul `indexRR` și crește `indexRR` cu 1, păstrându-l în intervalul [0, nrHosts - 1].
- **Shortest Queue (SQ):**
    - `private void addTaskSQ(Task task)` - Adaugă sarcina `task` în coada de priorități a host-ului cu cea mai mică dimensiune a cozii.
- **Size Interval Task Assignment (SITA):**
    - `private void addTaskSITA(Task task)` - Adaugă sarcina `task` în coada de priorități a host-ului cu indexul corespunzător tipului de sarcină.
        - Scurt: 0
        - Mediu: 1
        - Lung: 2
- **Least Work Left (LWL):**
    - `private void addTaskLWL(Task task)` - Adaugă sarcina `task` în coada de priorități a host-ului cu cel mai mic volum de muncă rămas.

---
