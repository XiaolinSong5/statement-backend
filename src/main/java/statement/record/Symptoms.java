package statement.record;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonIgnoreProperties({ "alias", "code" })
public enum Symptoms {
    START_NIET(10L, "START_NIET", "Start probleem", 1),
    ACCU(4L, "ACCU", "Lege accu", 2),
    LEKKE_BAND(1L, "LEKKE_BAND", "Lekke band", 3),
    VERKEERDE_BRANDSTOF(12L, "VERKEERDE_BRANDSTOF", "Verkeerd getankt", 4),
    SLEUTEL_IN_VOERTUIG(3L, "SLEUTEL_IN_VOERTUIG", "Buitengesloten", 5),
    BIJGELUIDEN(8L, "BIJGELUIDEN", "Bijgeluiden", 6),
    CONTROLELAMPJES(17L, "CONTROLELAMPJES", "Controlelampjes", 7),
    ANDERS(0L, "ANDERS", "Anders", 100);
    
    private Long code;
    private String name;
    private String alias;
    private Integer priority;
    
    @JsonCreator
    public static Symptoms toSymptom(@JsonProperty("name") String name) {
       return Symptoms.valueOf(name);
    }
    
    private Symptoms(Long code, String name, String alias, Integer priority) {
       this.code = code;
       this.name= name;
       this.alias = alias;
       this.priority = priority;
    }

    public Long getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }

    public Integer getPriority() {
        return priority;
    }
}
//TODO Unused, might be used later when integrating with Flow.
// dont remove until used.
//const flowSymptoms: Symptom[] = [
// { code: 1, name: 'LEKKE_BAND', alias: 'Lekke band', priority: 130 },
// { code: 2, name: 'LEKKE_BAND_GEEN_RESERVE', alias: 'Lekke band, geen reservewiel', priority: 0 } ,
// { code: 3, name: 'SLEUTEL_IN_VOERTUIG', alias: 'Sleutel in voertuig' , priority: 100 },
// { code: 4, name: 'ACCU', alias: 'Accu', priority: 140 },
// { code: 5, name: 'GEEN_BRANDSTOF', alias: 'Geen brandstof', priority: 0 },
// { code: 7, name: 'AFGESLAGEN_TIJDENS_RIJDEN', alias: 'Afgeslagen tijdens rijden', priority: 0 },
// { code: 8, name: 'BIJGELUIDEN', alias: 'Bijgeluiden', priority: 0 },
// { code: 9, name: 'KOELPROBLEEM', alias: 'Koelprobleem', priority: 0 },
// { code: 10, name: 'START_NIET', alias: 'Start niet', priority: 120 },
// { code: 11, name: 'STOTTERT', alias: 'Stottert', priority: 0 },
// { code: 12, name: 'VERKEERDE_BRANDSTOF', alias: 'Verkeerde brandstof', priority: 0 },
// { code: 13, name: 'VERLICHTING', alias: 'Verlichting', priority: 0 },
// { code: 14, name: 'KOPPELING', alias: 'Koppeling', priority: 0 },
// { code: 15, name: 'VERSNELLINGSBAK', alias: 'Versnellingsbak', priority: 0 },
// { code: 16, name: 'LEKKAGE', alias: 'Lekkage', priority: 0 },
// { code: 17, name: 'CONTROLELAMPJES', alias: 'Controlelampjes', priority: 0 },
// { code: 18, name: 'REMMEN', alias: 'Remmen', priority: 0 },
// { code: 19, name: 'RAAMMECHANIEK', alias: 'Raammechaniek', priority: 0 },
// { code: 20, name: 'RUITENWISSER', alias: 'Ruitenwisser', priority: 0 },
// { code: 21, name: 'SLEUTEL_FUNCTIONEERT_NIET', alias: 'Sleutel functioneert niet', priority: 0 },
// { code: 22, name: 'STUURINRICHTING', alias: 'Stuurinrichting', priority: 0 },
// { code: 23, name: 'SLEUTEL_VERLOREN', alias: 'Sleutel verloren', priority: 0 },
// { code: 24, name: 'AS', alias: 'As', priority: 0 },
// { code: 25, name: 'AS_STOMP', alias: 'As stomp', priority: 0 },
// { code: 26, name: 'BEPLATING', alias: 'Beplating', priority: 0 },
// { code: 27, name: 'CAROSSERIE', alias: 'Carosserie', priority: 0 },
// { code: 28, name: 'DISSEL', alias: 'Dissel', priority: 0 },
// { code: 29, name: 'DRAAGARM', alias: 'Draagarm', priority: 0 },
// { code: 30, name: 'HAGELSCHADE', alias: 'Hagelschade', priority: 0 },
// { code: 31, name: 'INTERIEUR', alias: 'Interieur', priority: 0 },
// { code: 32, name: 'LUIK', alias: 'Luik', priority: 0 },
// { code: 33, name: 'MOVER', alias: 'Mover', priority: 0 },
// { code: 34, name: 'OPLOOPREM', alias: 'Oplooparm', priority: 0 },
// { code: 35, name: 'STEKKERS', alias: 'Stekkers', priority: 0 },
// { code: 36, name: 'TRAPJE_ELEKTRISCH', alias: 'Trapje elektrisch', priority: 0 },
// { code: 37, name: 'UITZETPOTEN', alias: 'Uitzetpoten', priority: 0 },
// { code: 38, name: 'WIELLAGER', alias: 'Wiellager', priority: 0 },
// { code: 40, name: 'ELEKTRONICA', alias: 'Elektronica', priority: 110 },
// { code: 43, name: 'SLOTEN', alias: 'Sloten', priority: 0 },
// { code: 46, name: 'VERING', alias: 'Vering', priority: 0 },
// { code: 48, name: 'DIEFSTAL_VOLLEDIG_VOERTUIG', alias: 'Diefstal volledig voertuig', priority: 0 },
// { code: 49, name: 'RUITBREUK', alias: 'Ruitbreuk', priority: 0 },
// { code: 50, name: 'VOERTUIG_TERRUGGEVONDEN_NA_DIEFSTAL', alias: 'Voertuig teruggevonden na diefstal', priority: 0 },
// { code: 51, name: 'UITVAL_BESTUURDER', alias: 'Uitval bestuurder', priority: 0 }
//];