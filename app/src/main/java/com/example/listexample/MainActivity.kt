package com.example.listexample

import android.graphics.fonts.FontStyle
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.listexample.ui.theme.ListexampleTheme

// Data class to hold information for each grid item
data class GridItemData(
    val name: String,
    val imageResId: Int,  // Resource ID for the image
    val description: String,
    val rootwords:List<String> = emptyList(),
    val synonyms: List<String> = emptyList(), // Default empty list
    val antonyms: List<String> = emptyList(),
    val fillers: List<String> = emptyList(),
    val oneWordSubstitutes: List<String> = emptyList()
)
val items = listOf(
    GridItemData(
        "Root Words", R.drawable.im1, "A root word is the base form of a word.",
        rootwords = listOf("1. Aqua-water-Aquarium: A tank for water-dwelling creatures.",
    "2. Bio-life-Biology: The study of life and living organisms.",
    "3. Chron-time-Chronology: The science of arranging events in their order of occurrence.",
    "4. Geo-earth-Geography: The study of the physical features of the earth.",
    "5. Meter-measure-Thermometer: An instrument used to measure temperature.",
    "6. Phone-sound-Microphone: A device used to record or amplify sound.",
    "7. Graph-writing, drawing-Autograph: A signature, especially that of a celebrity.",
    "8. Port-carry-Transport: To carry goods from one place to another.",
    "9. Vid-see-Video: A recording of moving visual images.",
    "10. Auto-self-Autonomous: Capable of operating independently.",
    "11. Thermo-heat-Thermometer: A device used to measure heat.",
    "12. Poly-many-Polygon: A shape with many sides.",
    "13. Uni-one-Unicycle: A vehicle with one wheel.",
    "14. Micro-small-Microscope: An instrument for viewing very small objects.",
    "15. Macro-large-Macroeconomics: The branch of economics concerned with large-scale factors.",
    "16. Man-hand-Manicure: A cosmetic treatment for the hands and nails.",
    "17. Scribe-write-Transcribe: To write down or record.",
    "18. Act-do-Reaction: To respond or behave in a particular way.",
    "19. Ject-throw-Eject: To throw something out.",
    "20. Tact-touch-Tactile: Relating to or involving the sense of touch.",
    "21. Form-shape-Transform: To change in shape or form.",
    "22. Vok-voice-Revoke: To cancel or take back.",
    "23. Luc-light-Illuminate: To light up.",
    "24. Ver-truth-Verify: To confirm the truth of something.",
    "25. Ped-foot-Pedestrian: A person who is walking, especially in a city or town.",
    "26. Flect-bend-Reflect: To bounce back, often light.",
    "27. Clus-close-Enclose: To surround or close off.",
    "28. Cide-kill-Homicide: The act of killing someone.",
    "29. Duc-lead-Conduct: To lead or guide.",
    "30. Spect-look-Inspect: To look at something carefully.",
    "31. Cyc-circle-Cycling: The activity of riding a bicycle.",
    "32. Flu-flow-Influence: The flow of power or control.",
    "33. Lingu-language-Linguistic: Related to language.",
    "34. Son-sound-Sonar: A device used for detecting objects underwater.",
    "35. Path-feeling, disease-Sympathy: Feelings of pity and sorrow for someone's misfortune.",
    "36. Lux-light-Illuminate: To light up or brighten.",
    "37. Manu-hand-Manual: A book of instructions or guidelines.",
    "38. Dyna-power, energy-Dynamic: Characterized by constant change or activity."
)

//        synonyms = listOf("Base word", "Fundamental word"),
//        antonyms = listOf("Derived word"),
//        fillers = listOf("Essential", "Primary"),
//        oneWordSubstitutes = listOf("Root", "Base")
    ),
    GridItemData(
        "Synonyms", R.drawable.im2, "Synonyms are words with similar meanings.",
        synonyms = listOf("1. Quick-Fast-Rapid: He made a quick decision.",
    "2. Happy-Joyful-Content: She felt happy after receiving the good news.",
    "3. Strong-Powerful-Robust: The athlete is strong and powerful.",
    "4. Smart-Intelligent-Clever: He is known for his smart solutions to problems.",
    "5. Beautiful-Gorgeous-Stunning: The sunset was beautiful and breathtaking.",
    "6. Brave-Courageous-Valiant: The soldier was brave in battle.",
    "7. Easy-Simple-Straightforward: The instructions were easy to follow.",
    "8. Large-Big-Huge: The elephant is large and heavy.",
    "9. Quick-Speedy-Fast: The cheetah is known for its quick and speedy movements.",
    "10. Expensive-Costly-Pricey: The diamond ring was very expensive.",
    "11. New-Recent-Fresh: She bought a new dress for the occasion.",
    "12. Bright-Luminous-Shiny: The bright light hurt his eyes.",
    "13. Quiet-Silent-Hushed: The library was quiet and peaceful.",
    "14. Strong-Resilient-Tough: The tough fabric can withstand wear and tear.",
    "15. Warm-Hot-Comfortable: The soup was warm and comforting.",
    "16. Smart-Quick-Witty: She gave a smart and witty response.",
    "17. Sad-Sorrowful-Gloomy: The news made him feel sad and gloomy.",
    "18. Rich-Wealthy-Affluent: The businessman became wealthy after his success.",
    "19. Old-Aged-Venerable: The old tree stood in the middle of the field.",
    "20. Tall-High-Soaring: The building was tall and impressive.",
    "21. Small-Tiny-Minute: The bug was so small it was hard to see.",
    "22. Hard-Difficult-Challenging: The exam was hard and challenging.",
    "23. Safe-Secure-Protected: The children felt safe in their homes.",
    "24. Simple-Easy-Plain: The design was simple and elegant.",
    "25. Clean-Tidy-Neat: The house was clean and tidy after the cleaning.",
    "26. Cold-Freezing-Chilly: The cold wind made everyone shiver.",
    "27. Bright-Vivid-Radiant: The colors in the painting were bright and vivid.",
    "28. Expensive-Luxurious-Premium: The car was a luxurious and expensive model.",
    "29. Quiet-Tranquil-Peaceful: The garden was quiet and tranquil.",
    "30. Ancient-Old-Historic: The ruins were ancient and full of history.",
    "31. Angry-Furious-Irritated: She was angry about the misunderstanding.",
    "32. Friendly-Amiable-Cordial: The dog is very friendly and loves to play.",
    "33. Creative-Innovative-Imaginative: The artist was known for his creative ideas.",
    "34. Exciting-Interesting-Fascinating: The movie was exciting and full of surprises.",
    "35. Powerful-Influential-Dominant: The powerful leader made a significant impact."
)


//        fillers = listOf("Speed", "Velocity"),
//        oneWordSubstitutes = listOf("Expedited", "Accelerated")
    ),
    GridItemData(
        "Antonyms", R.drawable.im3, "Antonyms are words with opposite meanings.",
        antonyms = listOf("1. Quick-Slow: The rabbit is quick, while the tortoise is slow.",
    "2. Happy-Sad: She was happy when she received the gift, but sad when it broke.",
    "3. Strong-Weak: The strong man carried the heavy box, but the weak one struggled.",
    "4. Smart-Dumb: He is smart and solves problems quickly, while his friend is often dumbfounded.",
    "5. Beautiful-Ugly: The sunset was beautiful, but the storm clouds made the sky ugly.",
    "6. Brave-Cowardly: The brave knight faced the dragon, but the cowardly soldier ran away.",
    "7. Easy-Difficult: The test was easy for some, but difficult for others.",
    "8. Large-Small: The elephant is large, while the mouse is small.",
    "9. Quick-Slow: The cheetah is quick, but the sloth is slow.",
    "10. Expensive-Cheap: The designer handbag was expensive, while the generic one was cheap.",
    "11. New-Old: She bought a new car, but her brother's car was old.",
    "12. Bright-Dull: The bright neon lights were blinding, while the old lamp gave a dull glow.",
    "13. Quiet-Loud: The library was quiet, but the concert hall was loud.",
    "14. Strong-Feeble: The storm was strong, but the weak tree fell over.",
    "15. Warm-Cold: The coffee was warm, but the ice cream was cold.",
    "16. Smart-Stupid: She gave a smart answer, while he gave a stupid one.",
    "17. Sad-Happy: He was sad to leave, but happy to return home.",
    "18. Rich-Poor: The rich businessman lives in a mansion, while the poor man lives in a small apartment.",
    "19. Old-Young: The old oak tree stood tall, but the young sapling struggled to grow.",
    "20. Tall-Short: The mountain was tall, while the hill was short.",
    "21. Small-Large: The cat is small, but the dog is large.",
    "22. Hard-Soft: The rock was hard, but the pillow was soft.",
    "23. Safe-Dangerous: The beach was safe, but the cliff was dangerous.",
    "24. Simple-Complex: The math problem was simple for him, but complex for others.",
    "25. Clean-Dirty: The room was clean, but the kitchen was dirty.",
    "26. Cold-Hot: The cold wind made her shiver, while the hot sun made her sweat.",
    "27. Bright-Dark: The bright lights of the city contrasted with the dark night sky.",
    "28. Expensive-Inexpensive: The luxury car was expensive, while the economy car was inexpensive.",
    "29. Quiet-Noisy: The house was quiet until the noisy children arrived.",
    "30. Ancient-Modern: The ancient ruins contrasted with the modern skyscrapers around them.",
    "31. Angry-Calm: He was angry after the argument, but she stayed calm.",
    "32. Friendly-Unfriendly: The dog was friendly, but the cat was unfriendly.",
    "33. Creative-Unimaginative: The creative artist came up with an innovative design, while the teacher was unimaginative.",
    "34. Exciting-Boring: The concert was exciting, but the lecture was boring.",
    "35. Powerful-Helpless: The powerful warrior defeated the enemy, but the helpless prisoner couldn't escape."
)

//        fillers = listOf("Reverse", "Opposite"),
//        oneWordSubstitutes = listOf("Reduction", "Fall")
    ),
    GridItemData(
        "Fillers", R.drawable.im4, "Fillers are words that hold a place in speech or writing.",
        fillers = listOf("1. Actually-: Actually, I don't think it's a good idea.",
    "2. Basically-: Basically, what we're trying to say is that we need more time.",
    "3. You know-: I was, you know, thinking about what we could do for dinner.",
    "4. Like-: I was like, 'That's such a great idea!'",
    "5. So-: So, I went to the store, and then I came back home.",
    "6. I mean-: I mean, it's not that difficult to understand.",
    "7. Right-: It's, right, not something I would do.",
    "8. Well-: Well, I'm not sure if we can make it on time.",
    "9. Honestly-: Honestly, I didn't expect that to happen.",
    "10. Basically-: Basically, we just need to finish the project by the end of the week.",
    "11. In fact-: In fact, I have no idea where she went.",
    "12. Literally-: He literally ran a mile without stopping.",
    "13. To be honest-: To be honest, I thought the movie was a bit too long.",
    "14. I guess-: I guess I'll just have to wait until it's my turn.",
    "15. Kinda-: I kinda like the idea, but it needs some improvements.",
    "16. Sort of-: I'm sort of interested in going to that event, but I'm not sure yet.",
    "17. Probably-: We'll probably have to leave in a few minutes.",
    "18. Actually-: Actually, now that I think about it, we might need a different approach.",
    "19. Well-: Well, that was unexpected!",
    "20. I think-: I think we should reconsider our options.",
    "21. Maybe-: Maybe we could try that new restaurant for lunch.",
    "22. I mean-: I mean, everyone else thought it was a good idea.",
    "23. You know what I mean-: It's just, you know what I mean, sometimes things just don't go as planned.",
    "24. It's like-: It's like when you try to cook something and it doesn't turn out right.",
    "25. Anyway-: Anyway, let's move on to the next topic.",
    "26. I suppose-: I suppose we could give it a shot and see how it goes.",
    "27. Of course-: Of course, I will help you with that task.",
    "28. Just-: Just let me know when you're ready to leave.",
    "29. Alright-: Alright, we can talk more about it later.",
    "30. Actually-: Actually, I think I’ve changed my mind about that decision."
)

//        synonyms = listOf("Um", "Ah"),
//        antonyms = listOf("None"),

//        oneWordSubstitutes = listOf("Transition", "Pause")
    ),
    GridItemData(
        "One Word Substitutes", R.drawable.im5, "One word substitutes replace long phrases with a single word.",
        oneWordSubstitutes = listOf("1. Abandon-: To give up completely, e.g., He abandoned his car after it broke down.",
    "2. Abundant-: More than enough, e.g., The country has abundant natural resources.",
    "3. Aggressive-: Ready to attack or start fights, e.g., The aggressive dog bit the mailman.",
    "4. Benevolent-: Kind and charitable, e.g., She is known for her benevolent acts in the community.",
    "5. Cautious-: Careful to avoid danger or risks, e.g., He took a cautious approach to the situation.",
    "6. Deceptive-: Misleading or dishonest, e.g., His deceptive behavior fooled everyone.",
    "7. Eccentric-: Unconventional or strange, e.g., The eccentric artist painted abstract portraits.",
    "8. Exhilarating-: Making you feel very happy, e.g., The rollercoaster ride was exhilarating.",
    "9. Futile-: Pointless or useless, e.g., It was futile to argue with him.",
    "10. Gregarious-: Sociable, e.g., He is a gregarious person who loves to meet new people.",
    "11. Hostile-: Unfriendly or antagonistic, e.g., There was a hostile atmosphere at the meeting.",
    "12. Immaculate-: Perfectly clean, e.g., Her house was immaculate, with everything in place.",
    "13. Jeopardize-: To put at risk, e.g., His actions could jeopardize the success of the project.",
    "14. Keen-: Eager or enthusiastic, e.g., She has a keen interest in literature.",
    "15. Meticulous-: Very careful and precise, e.g., The engineer was meticulous in his calculations.",
    "16. Nomadic-: Moving from place to place, e.g., The nomadic tribe travels through the desert.",
    "17. Obsolete-: Out of date, e.g., The technology has become obsolete and is no longer used.",
    "18. Pessimistic-: Expecting the worst, e.g., He has a pessimistic view of the future.",
    "19. Quirky-: Unusual in a positive way, e.g., She has a quirky sense of humor.",
    "20. Resilient-: Able to recover quickly from difficult conditions, e.g., The community showed a resilient spirit after the storm.",
    "21. Sophisticated-: Advanced and complex, e.g., The sophisticated software helped improve productivity.",
    "22. Tolerant-: Willing to accept others, e.g., The city is known for its tolerant attitude towards diverse cultures.",
    "23. Unanimous-: Fully in agreement, e.g., The decision was unanimous among the board members.",
    "24. Vigilant-: Watchful or alert, e.g., The security guard remained vigilant throughout the night.",
    "25. Widespread-: Existing or happening in many places, e.g., There is widespread support for the new policy."
)

//        synonyms = listOf("Quit: He decided to quit smoking.", "Terminate: She terminated the contract."),
//        antonyms = listOf("Continue"),
//        fillers = listOf("Stop", "Cease"),

    )
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListexampleTheme {
                // Initialize NavController
                val navController = rememberNavController()
                // Set up the navigation graph
                NavHost(navController = navController, startDestination = "grid") {
                    composable("grid") {
                        Myapp(navController)
                    }
                    composable(
                        "description/{name}/{imageResId}/{description}",
                        arguments = listOf(
                            navArgument("name") { type = NavType.StringType },
                            navArgument("imageResId") { type = NavType.IntType },
                            navArgument("description") { type = NavType.StringType }
                        )
                    ) { backStackEntry ->
                        // Extract arguments
                        val name = backStackEntry.arguments?.getString("name") ?: ""
                        val imageResId = backStackEntry.arguments?.getInt("imageResId") ?: 0
                        val description = backStackEntry.arguments?.getString("description") ?: ""
                        DescriptionScreen(navController,name, imageResId, description)
                    }
                }
            }
        }
    }
}

@Composable
fun Myapp(navController: NavHostController) {
    // Create a list of items with different names, images, and descriptions
    val items = listOf(
        GridItemData("Root Words", R.drawable.im1, "A root word is the base form of a word"),
        GridItemData("Synonyms", R.drawable.im2, "Synonyms are words with similar meanings"),
        GridItemData("Antonyms", R.drawable.im3, "Antonyms are words with opposite meanings"),
        GridItemData("Fillers", R.drawable.im4, "Fillers are words that hold a place in speech or writing"),
        GridItemData("One Word Substitutes", R.drawable.im5, "One word substitutes replace long phrases with a single word")
    )
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar()

        // LazyVerticalGrid to display items in 2 columns
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(18.dp)
                .fillMaxSize(),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(items) { item ->
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .border(BorderStroke(1.dp, Color.Gray)) // Add a border around each item
                        .padding(8.dp) // Padding inside the border
                ) {
                    ImageWithDescription(item = item, navController = navController)
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    // Create a TopAppBar for the header
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "English Hand Book", // Title text in the top bar
                    style = MaterialTheme.typography.titleLarge

                )
            }
        },
        actions = {
            // You can add more actions (e.g., menu icon) here
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = Color.Blue,
            titleContentColor = Color.White
        )
    )
}



@Composable
fun ImageWithDescription(item: GridItemData, navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Image item with a clickable modifier
        Image(
            painter = painterResource(id = item.imageResId), // Dynamic image resource
            contentDescription = "Image for ${item.name}",
            modifier = Modifier
                .size(150.dp)
                .clickable {
                    // Navigate to description screen with item data
                    navController.navigate("description/${item.name}/${item.imageResId}/${item.description}")
                }
        )
        Spacer(modifier = Modifier.height(8.dp))
        // Name of the item
        Text(
            text = item.name,
            fontSize = 20.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DescriptionScreen(navController: NavHostController,name: String, imageResId: Int, description: String) {
    val item = items.find { it.name == name } ?: return

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(9.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(
            title = {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    //contentAlignment = Alignment.Center
                ) {
                    Text("Back")
                } },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Blue,
                titleContentColor = Color.White
            )
        )

        // Image for the item
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = "Image for $name",
            modifier = Modifier
                .size(320.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        // Name of the item
        Text(
            text = name,
            fontSize = 24.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(10.dp))
        // Description text
        Text(
            text = description,
            fontSize = 18.sp,
            color = Color.LightGray,
            textAlign = TextAlign.Start, // Align text to the left
            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
        )
        Spacer(modifier = Modifier.height(10.dp))
        //display rootwords
        if (item.rootwords.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp) // Set the desired height for scrollable area
            ) {
                //Text(text = "Synonyms", fontSize = 20.sp, color = Color.Black)
                items(item.rootwords) { rootword ->
                    Text(
                        text = rootword,
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                        textAlign = TextAlign.Start
                    )
                }
            }
        }


        // Display Synonyms
        if (item.synonyms.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp) // Set the desired height for scrollable area
            ) {
                //Text(text = "Synonyms", fontSize = 20.sp, color = Color.Black)
                items(item.synonyms) { synonym ->
                    Text(
                        text = synonym,
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                        textAlign = TextAlign.Start
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        // Display Antonyms
        if (item.antonyms.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp) // Set the desired height for scrollable area
            ) {

                //Text(text = "Antonyms", fontSize = 20.sp, color = Color.Black)
                items(item.antonyms) { antonym ->
                    Text(
                        text = antonym,
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                        textAlign = TextAlign.Start
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        // Display Fillers
        if (item.fillers.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp) // Set the desired height for scrollable area
            ) {
                //Text(text = "Fillers", fontSize = 20.sp, color = Color.Black)
                items(item.fillers) { filler ->
                    Text(
                        text = filler,
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                        textAlign = TextAlign.Start
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        // Display One Word Substitutes
        if (item.oneWordSubstitutes.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp) // Set the desired height for scrollable area
            ) {
                //Text(text = "One Word Substitutes", fontSize = 20.sp, color = Color.Black)
                items(item.oneWordSubstitutes) { substitute ->
                    Text(
                        text = substitute,
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                        textAlign = TextAlign.Start
                    )
                }
            }
        }


    }
}
