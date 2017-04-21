import armorsetsearch.AllEquipments;
import armorsetsearch.ArmorSearchWrapper;
import armorsetsearch.skillactivation.SkillActivationRequirement;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import models.CharmData;
import models.Decoration;
import models.Equipment;
import models.EquipmentType;
import ui.MonsterHunterArmorSearcher;
import utils.CsvReader;

public class Main {

    private static final String FILE_PATH_HEAD_EQUIPMENT = "data/MH_EQUIP_HEAD.csv";
    private static final String FILE_PATH_BODY_EQUIPMENT = "data/MH_EQUIP_BODY.csv";
    private static final String FILE_PATH_ARM_EQUIPMENT = "data/MH_EQUIP_ARM.csv";
    private static final String FILE_PATH_WST_EQUIPMENT = "data/MH_EQUIP_WST.csv";
    private static final String FILE_PATH_LEG_EQUIPMENT = "data/MH_EQUIP_LEG.csv";
    private static final String FILE_PATH_SKILL_ACTIVATION = "data/MH_SKILL_TRANS.csv";
    private static final String FILE_PATH_DECORATION = "data/MH_DECO.csv";
    private static final String FILE_PATH_CHARM = "data/MH_CHARM_TABLE.csv";

    public static void main(String args[]) throws IOException {
        // Parse CSV
        List<Equipment> headEquipments = CsvReader.getEquipmentFromCsvFile(FILE_PATH_HEAD_EQUIPMENT, EquipmentType.HEAD);
        List<Equipment> bodyEquipments = CsvReader.getEquipmentFromCsvFile(FILE_PATH_BODY_EQUIPMENT, EquipmentType.BODY);
        List<Equipment> armEquipments = CsvReader.getEquipmentFromCsvFile(FILE_PATH_ARM_EQUIPMENT, EquipmentType.ARM);
        List<Equipment> wstEquipments = CsvReader.getEquipmentFromCsvFile(FILE_PATH_WST_EQUIPMENT, EquipmentType.WST);
        List<Equipment> legEquipments = CsvReader.getEquipmentFromCsvFile(FILE_PATH_LEG_EQUIPMENT, EquipmentType.LEG);

        AllEquipments allEquipments = new AllEquipments(headEquipments, bodyEquipments, armEquipments, wstEquipments, legEquipments);

        Map<String, List<SkillActivationRequirement>> skillActivationChartMap = CsvReader.getSkillActivationRequirementFromCsvFile(FILE_PATH_SKILL_ACTIVATION);
        Map<String, List<Decoration>> decorationLookupTable = CsvReader.getDecorationFromCsvFile(FILE_PATH_DECORATION);
        Map<String, List<CharmData>> charmLookupTable = CsvReader.getCharmFromCsvFile(FILE_PATH_CHARM);

        ArmorSearchWrapper armorSearchWrapper = new ArmorSearchWrapper(allEquipments,
                                                                       skillActivationChartMap,
                                                                       decorationLookupTable,
                                                                       charmLookupTable);

        MonsterHunterArmorSearcher monsterHunterArmorSearcher = new MonsterHunterArmorSearcher();
        monsterHunterArmorSearcher.init(armorSearchWrapper);
    }
}
