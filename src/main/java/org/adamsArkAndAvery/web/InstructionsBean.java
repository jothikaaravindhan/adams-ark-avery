package org.adamsArkAndAvery.web;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "instructionsBean")
@SessionScoped
public class InstructionsBean implements Serializable{
	private String petType;
	
	public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }
    
    public String getInstructions() {
    	String text;
    	if ("Dog".equals(petType)) {
    		text = "A) Dogs\r\n"
    				+ "\r\n"
    				+ "Feeding: [FOOD BRAND], [AM/PM PORTION], [ADD-INS], [RESTRICTIONS]. Separate pets while feeding.\r\n"
    				+ "Water: Refresh; rinse bowl.\r\n"
    				+ "Exercise: [X] walks/day, [DURATION] each. Use [HARNESS TYPE]. No off-lead unless owner-approved and fenced.\r\n"
    				+ "Potty: Normal? Note diarrhea/constipation/straining/blood.\r\n"
    				+ "Enrichment: Sniff walk, puzzle feeder, basic cues (sit, down, touch).\r\n"
    				+ "Safety: Avoid heat/hot pavement; watch reactivity triggers [DOGS/BIKES/KIDS].\r\n"
    				+ "Red flags: Refuses food, repeated vomiting/diarrhea, labored breathing, bloated abdomen, collapse, pale gums.";
    	} else if  ("Cat".equals(petType)) {
    		text = "B) Cats\r\n"
    				+ "\r\n"
    				+ "Feeding: [WET/DRY], [PORTION], [TIMES]. Separate from other pets.\r\n"
    				+ "Water: Fresh; rinse bowls/fountain.\r\n"
    				+ "Litter: Scoop each visit; top up; note urine/clump size and stool quality.\r\n"
    				+ "Enrichment: Wand toy 5–10 min, food puzzles, window perch.\r\n"
    				+ "Home: Doors/windows/screens secure; no access to toxic plants (lilies!), strings, hair ties.\r\n"
    				+ "Red flags: No urination in 24h, straining, crying in box, hiding, open-mouth breathing, sudden drooling (possible toxin).";
    	} else if  ("Bird".equals(petType)) {
    		text = "C) Birds (parrots, finches, etc.)\r\n"
    				+ "\r\n"
    				+ "Feeding: Species-appropriate pellets + measured seeds + fresh veg/fruit per Owner Notes. Remove perishables after 2–3h.\r\n"
    				+ "Water: Change daily; clean dish.\r\n"
    				+ "Cage: Spot clean liner; wipe obvious mess; confirm perch stability.\r\n"
    				+ "Enrichment: Foraging toys, gentle talk time; out-of-cage only if explicitly allowed and room bird-proofed (fans/doors/windows off/closed).\r\n"
    				+ "Environment: No Teflon/PTFE fumes, aerosols, candles. Keep drafts down.\r\n"
    				+ "Red flags: Fluffed, tail-bobbing, sitting at bottom, closed eyes, not perching/eating, discharge from nares.";
    	} else if  ("Reptile".equals(petType)) {
    		text = "D) Reptiles (e.g., gecko, bearded dragon, snake, turtle)\r\n"
    				+ "\r\n"
    				+ "Feeding: Per species schedule [PREY/INSECTS/VEG], dusting per Owner Notes. Never handle immediately after feeding.\r\n"
    				+ "Water/Humidity: Fresh water; mist or fill humid hide as listed on enclosure card.\r\n"
    				+ "Heat/Light: Verify basking/ambient temps and UVA/UVB lights are ON as labeled; do not change settings unless instructed.\r\n"
    				+ "Cleaning: Spot clean feces/urates; replace soiled substrate area.\r\n"
    				+ "Handling: Only if authorized; support whole body; no grabs by tail/head.\r\n"
    				+ "Red flags: Not thermoregulating, lethargy, sunken eyes, wheezing/bubbles, stuck shed around eyes/toes, refusal to eat beyond owner-noted pattern.";
    	} else if  ("Exotic".equals(petType)) {
    		text = "H) “Exotic Pet” (catch-all—e.g., sugar glider, hedgehog, invertebrates, specialty species)\r\n"
    				+ "\r\n"
    				+ "Follow the Owner’s Species Card strictly. If a step is unclear, call before proceeding.\r\n"
    				+ "\r\n"
    				+ "Feeding/Water: As listed; respect nocturnal schedules.\r\n"
    				+ "\r\n"
    				+ "Temperature/Lighting/Humidity: Verify per enclosure card.\r\n"
    				+ "\r\n"
    				+ "Handling: Only if explicitly authorized; many exotics stress easily.\r\n"
    				+ "\r\n"
    				+ "Red flags: Any abrupt change in appetite, posture, coordination, or respiration.";
    	} else if  ("Fish".equals(petType)) {
    		text = "E) Fish (freshwater/saltwater)\r\n"
    				+ "\r\n"
    				+ "Feeding: Small amounts once per visit unless otherwise noted; remove uneaten after 2–3 minutes.\r\n"
    				+ "Equipment: Confirm filter/heater/air pump running; check thermometer, water level (top off with treated or owner-provided water only).\r\n"
    				+ "Lights: Follow timer; don’t leave on 24/7.\r\n"
    				+ "Cleaning: Do not change filter media or do water changes unless scheduled.\r\n"
    				+ "Red flags: Gasping at surface, clamped fins, flashing/scratching, white spots, sudden deaths, leaking tank.";
    	} else if  ("Amphibian".equals(petType)) {
    		text = "F) Amphibians (axolotl, newt, frog)\r\n"
    				+ "\r\n"
    				+ "Feeding: As per species (worms/pellets); remove leftovers promptly.\r\n"
    				+ "Water: Dechlorinated; temperature per tank card (often cool for axolotls).\r\n"
    				+ "Handling: Avoid unless necessary; wet, gloved hands only if required.\r\n"
    				+ "Environment: Low light, stable temps; no sharp decor.\r\n"
    				+ "Red flags: Floating, skin sloughing, reddened limbs/gills, refusal to eat beyond normal cycle.";
    	} else {
    		text = "G) Small Mammals (rabbit, guinea pig, hamster, rat, ferret, etc.)\r\n"
    				+ "\r\n"
    				+ "Feeding:\r\n"
    				+ "\r\n"
    				+ "Rabbits/Guinea pigs: Unlimited hay, measured pellets, fresh greens per Owner Notes; vitamin C for cavies if noted.\r\n"
    				+ "\r\n"
    				+ "Others: Species-specific pellets + veg/treats as listed.\r\n"
    				+ "Water: Check bottle flow and bowl; clean nozzle.\r\n"
    				+ "Habitat: Spot clean; refresh bedding/litter trays; safe chew toys.\r\n"
    				+ "Exercise: Floor time if allowed; rabbit-proof cords; supervise.\r\n"
    				+ "Red flags: Not eating/pooping (urgent in rabbits/guinea pigs), wet chin, diarrhea, head tilt, breathing noise, lethargy.";
    	}
    	return text.replace("\r\n", "<br/>").replace("\n", "<br/>");
    }
}
