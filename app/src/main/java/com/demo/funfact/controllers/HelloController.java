package com.demo.funfact.controllers;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.MediaType;

@Controller
public class HelloController {

	@GetMapping("/")
	public String hello(Model model) {
        model.addAttribute("fact", GetRandomFact());
		return "hello";
    }

    @GetMapping(path = "/health")
    @ResponseBody
    public String health() {
        return "OK";
    }
    
    @GetMapping("/fact")
    @ResponseBody
	public String greeting() {
		return GetRandomFact();
    }
    
    String GetRandomFact() {
        String[] facts = {
            "Banging your head against a wall for one hour burns 150 calories.",
            "In Switzerland it is illegal to own just one guinea pig.",
            "Pteronophobia is the fear of being tickled by feathers.",
            "Snakes can help predict earthquakes.",
            "A flock of crows is known as a murder.",
            "The oldest \"your mom\" joke was discovered on a 3,500 year old Babylonian tablet.",
            "So far, two diseases have successfully been eradicated: smallpox and rinderpest.",
            "29th May is officially \"Put a Pillow on Your Fridge Day\".",
            "Cherophobia is an irrational fear of fun or happiness.",
            "7% of American adults believe that chocolate milk comes from brown cows.",
            "If you lift a kangaroo's tail off the ground it can't hop.",
            "Bananas are curved because they grow towards the sun.",
            "Billy goats urinate on their own heads to smell more attractive to females.",
            "The inventor of the Frisbee was cremated and made into a Frisbee after he died.",
            "During your lifetime, you will produce enough saliva to fill two swimming pools.",
            "If Pinocchio says \"My Nose Will Grow Now\", it would cause a paradox.",
            "Polar bears could eat as many as 86 penguins in a single sitting…",
            "King Henry VIII slept with a gigantic axe beside him.",
            "Movie trailers were originally shown after the movie, which is why they were called \"trailers\".",
            "An eagle can kill a young deer and fly away with it.",
            "Heart attacks are more likely to happen on a Monday.",
            "Tennis players are not allowed to swear when they are playing in Wimbledon.",
            "In 2017 more people were killed from injuries caused by taking a selfie than by shark attacks.",
            "The top six foods that make your fart are beans, corn, bell peppers, cauliflower, cabbage and milk.",
            "There is a species of spider called the Hobo Spider.",
            "A lion's roar can be heard from 5 miles away.",
            "Saint Lucia is the only country in the world named after a woman.",
            "A baby spider is called a spiderling.",
            "The United States Navy has started using Xbox controllers for their periscopes.",
            "The following can be read forward and backwards: Do geese see God?",
            "A baby octopus is about the size of a flea when it is born.",
            "A sheep, a duck and a rooster were the first passengers in a hot air balloon.",
            "In Uganda, around 48% of the population is under 15 years of age.",
            "The average male gets bored of a shopping trip after 26 minutes.",
            "In the 16th Century, Arab women could initiate a divorce if their husbands didn't pour coffee for them.",
            "Recycling one glass jar saves enough energy to watch television for 3 hours.",
            "After the premiere of \"16 and Pregnant,\" teen pregnancy rates dropped.",
            "Approximately 10-20% of U.S. power outages are caused by squirrels.",
            "Facebook, Instagram and Twitter are all banned in China.",
            "95% of people text things they could never say in person.",
            "Honeybees can recognize human faces.",
            "The Battle of Hastings didn't take place in Hastings.",
            "While trying to find a cure for AIDS, the Mayo Clinic made glow in the dark cats.",
            "A swarm of 20,000 bees followed a car for two days because their queen was stuck inside.",
            "Nearly 3% of the ice in Antarctic glaciers is penguin urine.",
            "Bob Dylan's real name is Robert Zimmerman.",
            "A crocodile can't poke its tongue out.",
            "Sea otters hold hands when they sleep so they don't drift away from each other.",
            "A small child could swim through the veins of a blue whale.",
            "Bin Laden's death was announced on 1st May 2011. Hitler's death was announced on 1st May 1945.",
            "J.K. Rowling chose the unusual name 'Hermione' so young girls wouldn't be teased for being nerdy.",
            "Hewlett-Packard's (also known as HP) name was decided in a coin toss in 1939.",
            "There is a total of 1,710 steps in the Eiffel Tower.",
            "The Pokémon Hitmonlee and Hitmonchan are based off of Bruce Lee and Jackie Chan.",
            "A woman tried to commit suicide by jumping off the Empire State Building. She jumped from the 86th floor but was blown back onto the 85th floor by a gust of wind.",
            "Pirates wore earrings because they believed it improved their eyesight.",
            "Los Angeles's full name is \"El Pueblo de Nuestra Senora la Reina de los Angeles de Porciuncula.\"",
            "The Twitter bird actually has a name – Larry.",
            "Octopuses have four pairs of arms.",
            "In the popular sitcom, Parks and Recreation, the writers had no idea Nick Offerman was a talented saxophone player when they wrote the Duke Silver plot line.",
            "It snowed in the Sahara desert for 30 minutes on the 18th February 1979.",
            "Mike Tyson once offered a zoo attendant 10,000 dollars to let him fight a gorilla.",
            "ABBA turned down 1 billion dollars to do a reunion tour.",
            "There has never been a verified snow leopard attack on a human being.",
            "The first alarm clock could only ring at 4 a.m.",
            "Birds don't urinate.",
            "Dying is illegal in the Houses of Parliaments.",
            "The most venomous jellyfish in the world is the Irukandji.",
            "The 20th of March is Snowman Burning Day.",
            "Queen Elizabeth can't sit on the Iron Throne from Game of Thrones.",
            "There is official Wizard of New Zealand.",
            "An apple, potato, and onion all taste the same if you eat them with your nose plugged.",
            "Vincent van Gogh only sold one painting in his lifetime.",
            "A company in Taiwan makes dinnerware out of wheat, so you can eat your plate!",
            "The average person walks the equivalent of five times around the world in their lifetime.",
            "Michael Jackson offered to make a Harry Potter musical, but J.K. Rowling rejected the idea.",
            "The world record for stuffing drinking straws into your mouth at once is 459.",
            "Nutella was invented during WWII, when hazelnuts were mixed into chocolate to extend chocolate rations.",
            "In 2011, more than 1 in 3 divorce filings in the U.S. contained the word \"Facebook.\"",
            "According to Genesis 1:20-22 the chicken came before the egg.",
            "Honeybees can get drunk on fermented tree sap.",
            "Tears contain a natural pain killer which reduces pain and improves your mood.",
            "Squirrels forget where they hide about half of their nuts.",
            "Millions of birds a year die from smashing into windows in the U.S. alone.",
            "Dolly Parton lost in a Dolly Parton look-alike contest.",
            "George W. Bush was once a cheerleader.",
            "In total, there are 205 bones in the skeleton of a horse.",
            "Coca-Cola owns all website URLs that can be read as ahh, all the way up to 62 h's.",
            "Each year there are more than 40,000 toilet related injuries in the United States.",
            "Strawberries can be red, yellow, green or white.",
            "Mewtwo is a clone of the Pokémon Mew, yet it comes before Mew in the Pokédex.",
            "Four people lived in a home for 6 months infested with about 2,000 brown recluse spiders, but none of them were harmed.",
            "Madonna suffers from brontophobia, which is the fear of thunder.",
            "In June 2017, the Facebook community reached 2 billion active users.",
            "Samuel L. Jackson requested to have a purple lightsaber in Star Wars in order for him to accept the part as Mace Windu.",
            "Paraskavedekatriaphobia is the fear of Friday the 13th.",
            "Kleenex tissues were originally used as filters in gas masks.",
            "In 1998, Sony accidentally sold 700,000 camcorders that had the technology to see through people's clothes.",
            "During your lifetime, you will spend around thirty-eight days brushing your teeth.",
            "Ronald McDonald is \"Donald McDonald\" in Japan because it makes pronunciation easier for the Japanese.",
            "Ants leave maps for other ants when they walk.",
            "The smallest bone in your body is in your ear.",
            "People with brain damage can suffer from \"joke addiction\".",
            "Most Korean people don't have armpit odor.",
            "Captive pandas sometimes fake pregnancies.",
            "There is a sea slug that is part animal and part plant.",
            "The common cold comes from camels.",
            "You can fire a gun in space.",
            "Ketchup was a medicine in the early 1800s.",
            "Only 5% of the ocean has been explored.",
            "The word \"emoji\" comes from the Japanese words \"e\" and \"moji\", which mean \"picture\" and \"character\".",
            "Grapes are fatally toxic to cats and dogs – even in small amounts.",
            "The Louvre in Paris is so large that it would take 100 days to look at each piece.",
            "In the 18th Century Smallpox scars would get you a job.",
            "Before 1913, you could legally mail a baby.",
            "The original London Bridge is now in Arizona.",
            "By 400BC, Persian engineers mastered the technique of storing ice in the middle of desert summers.",
            "Kit Harrington is related to the man who invented the flush toilet, and one of the Gunpowder Plot perpetrators.",
            "The word \"burrito\" means \"little donkey\" in Spanish.",
            "Hawaiian pizza is a Canadian invention.",
            "Every year, the Netherlands sends Canada 20,000 tulip bulbs.",
            "Jupiter has a \"lost\" moon.",
            "Famous French painter Claude Monet was only rich because he won the lottery.",
            "Before alarm clocks were affordable, there were professional \"Knocker Uppers\" to wake people up for work.",
            "Former President Lyndon B. Johnson's life was saved by a random toilet break.",
            "The coloring of Gizmo's fur from The Gremlins matches Steven Spielberg's dog.",
            "Japanese people use more paper for manga than toilet roll.",
            "Some insects and small birds see the world in slow motion.",
            "Chickens are the closest living relatives to the T-Rex.",
            "Adult Mayflies have no functional mouth.",
            "M&M's turned down product placement in ET.",
            "There is a type of mouse that howls to defend its territory.",
            "The words \"laser\" and \"radar\" were originally acronyms.",
            "Men are 23% more likely to be left-handed than women.",
            "In Japan, girls buy boys gifts on Valentine's Day.",
            "Over one quarter of the world's hazelnuts are used to make Nutella.",
            "Seeing Eye dogs can poop on command.",
            "A black cat called Luna once saved her owners from a house fire.",
            "Scientists put fake tails on chickens to understand how T-Rex might have walked.",
            "Mock naval battles were sometimes held in Rome's Colosseum.",
            "Male cats have longer tails than female cats.",
            "During WWII, The British Navy destroyed French vessels after France fell to Germany.",
            "The first death in the American Civil War was a horse.",
            "Johnny Cash has a species of tarantula named after him.",
            "The word \"disaster\" means \"bad star\" in Ancient Greek.",
            "The start-up sound for Window 95 was made on an Apple Macintosh computer.",
            "In the 18th Century, Napoleon and Benjamin Franklin were both defeated at chess by a machine.",
            "The world's loudest bird call can be heard from half a mile away.",
            "At one point, you could invest money in Somali Pirates.",
            "Steve Irwin has a snail named after him.",
            "Sierra Leone is the world's roundest country.",
            "The word \"y'all\" dates back to 1631.",
            "In Ethiopia, there is a place known as ‘The Gateway to Hell'.",
            "There is a nut on a helicopter called the ‘Jesus Nut'.",
            "Eating carrots can turn your skin orange.",
            "QWERTY keyboards were originally supposed to slow down typing.",
            "You get Goosebumps when you're scared to make you look bigger.",
            "Katy Perry and My Chemical Romance have done song covers for The Sims.",
            "Russia was founded by a group of Viking traders called the Rus.",
            "Car manufacturer Volkswagen makes sausages.",
            "Walt Disney World is the second-largest buyer of explosives in the U.S.",
            "John F. Kennedy was buried without his brain.",
            "The air in the subway is 15% human skin.",
            "In 1969, people on a hijacked plane thought they were on a prank hidden-camera show.",
            "In 1977, scientists received a signal from deep space.",
            "Cockroaches can live for weeks without their heads.",
            "A person adds 37 million bacteria to a room every hour.",
            "When you eat oysters raw, they're still alive.",
            "Lobsters have teeth in their stomach.",
            "In 1912, a French orphanage held a raffle, with babies as the prize.",
            "Mosquitos are the world's deadliest animal.",
            "Farm-raised salmon are dyed pink.",
            "If you fell into a volcano, you'd float on the lava instead of melting.",
            "The average person produces about half a pint of sweat each day from their feet.",
            "Pigs are very smart animals.",
            "It takes 600 cows to make one season's worth of NFL footballs.",
            "Prisoners at Alcatraz were given the luxury of hot showers.",
            "You can get a McDonald's Gold Card that entitles you to free food for life.",
            "The German Government thanked David Bowie for helping break down the Berlin Wall.",
            "The compound used to make dynamite is used for treating heart problems.",
            "Sometimes earthquakes naturally cause lights to appear.",
            "Nikola Tesla once paid for a hotel bill with a ‘death ray'.",
            "Freddie Mercury designed the Queen logo himself.",
            "The world's biggest epidemic started in America and killed over 100 million people.",
            "The Empire State Building cost nearly 1/3 less than budgeted.",
            "Cats don't naturally meow.",
            "David Hasslehoff has a crab named after him.",
            "Ernest Hemingway loved cats.",
            "Despite only being 3% of your body weight, the Brain consumes about 20% of your calorie intake.",
            "Crows can hold grudges against specific individual people.",
            "A goat called William Windsor served as a Lance Corporal in the British Army.",
            "Early helicopters had wooden rotor blades.",
            "Tomato ketchup has lots of health benefits.",
            "On Saturn's largest moon, Titan, a person could fly by attaching wings to their arms.",
            "Research shows that everyone has up to 6 doppelgängers.",
            "Canada has the world's smallest jail.",
            "Popcorn is the healthiest cinema snack and has serious health benefits.",
            "You can have your ashes turned into a firework.",
            "Russia defrosted some prehistoric worms, and two woke up.",
            "A 12-year-old French boy once faked his kidnapping to get out of going to the Dentist."};
        
        return facts[new Random().nextInt(facts.length)];
    }


}