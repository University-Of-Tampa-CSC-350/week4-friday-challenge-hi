[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/VaeURjcR)
# FC 004
This challenge can be worked on in pairs of 2 and is an extension of Challenge 3.
Focus for this challenge is on the topics we covered this week:
1. Safe-Args
2. Shared View Model
3. Fragment Container
4. SupportManager / FindNavController
## Github Classrooms
We will be using Github classroom to do in-class Friday Challenges.
You can make as many commits and as many pushes as needed to the main branch on your forked copy of the repo.
The notes about commits are still relevant here:
To be consistent, use the same styling for commit messages that was given in the Project I:
- PREFIX – Short description of the change
  A detailed description can be added to the commit in the long description, if needed.
  The following are the possible options for [Prefix]
- [FEAT] - For new features or major additions to the project.
  FEAT - Added button click-ability feature 
- [FIX] - For bug fixes, corrections, or revisions to the code.
  FIX - Corrected navigation bar alignment on mobile devices
- [STYLE] - For stylistic changes such as formatting, CSS modifications, or minor visual updates.
  STYLE - Updated color scheme for better contrast
- [DOCS] - For changes or additions to the documentation, including README files and comments in the code.
  DOCS - Added project description and setup instructions to README
- [SECURITY] - For changes related to improving the security of the website.
  SECURITY - Implemented input validation for contact form
- [REFACTOR] - For code refactoring that doesn’t change functionality but improves code quality or organization.
  REFACTOR - Organized attributes for button components files in Home layout.
- [TEST] - For adding tests or making changes to the testing suite.
  TEST - Added validation tests for login form input

### 5. Submitting your work
Once, you are sure that all the work is completed, go through the following steps for submission.
Push all your work onto the main branch. **Only the main branch** will be considered for grading.

## Project description
Various layouts defining a scenario from the first mission in a famous game titled - **Detroit Become Human** are provided to you.
Your goal is to arrange all the fragments in an appropriate order so that all the transitions 
are meaningful.
This time while also arranging the fragments, some fragments need to be deleted. Which ones to delete and which ones
to keep are mentioned below.s

Note that you **are not supposed** to add any widgets or any layouts, unless explicitly specified (Task #1).

### Task 1: Create a Shared ViewModel

Create a MissionViewModel class that will hold all meaningful mission decisions.

The ViewModel must store at minimum:
* selectedStrategy (String or Enum)
* helicopterDismissed (Boolean)
* trustLevel (Int) 

The ViewModel must be shared across fragments using activityViewModels().

#### Step 1: Create the ViewModel Class
Right-click your package → New → Kotlin Class → Name it:
MissionViewModel
Example structure:
``` 
import androidx.lifecycle.ViewModel
class MissionViewModel : ViewModel() {
    var selectedStrategy: String? = null
    var helicopterDismissed: Boolean = false
    var trustLevel: Int = 0
}
```
#### Step 2: Share the ViewModel Across Fragments
Inside each fragment that needs access to mission state, add:
```
import androidx.fragment.app.activityViewModels
private val viewModel: MissionViewModel by activityViewModels()
```
Important:
* Use activityViewModels() so the ViewModel is shared.
* Do NOT use viewModels() (that would create a separate instance per fragment).

#### Step 3: Update State When a Decision Is Made
Example inside a button click listener:
```
buttonEstablishContact.setOnClickListener {
    viewModel.selectedStrategy = "ESTABLISH_CONTACT"
    viewModel.trustLevel += 10
    findNavController().navigate(R.id.action_fragment1_to_fragment2)
}
```

Do not:
* Store decisions in local variables.
* Store decisions inside TextView values.
* Rely only on Safe Args for mission logic.
#### Step 4: Read State in the Final Fragment
Inside your final outcome fragment:
```
val strategy = viewModel.selectedStrategy
val trust = viewModel.trustLevel
```
Then compute outcome dynamically:
```
if (trust > 15) {
resultTextView.text = "Daniel surrenders. Emma survives."
} else {
resultTextView.text = "Negotiation collapses."
}
```
You must compute the result based on stored state — not on which fragment was navigated to.

### Task 2: Remove Hardcoded Outcome Fragments
Currently, the project contains multiple ending fragments.

Refactor the navigation so that:
* Only one final outcome fragment exists, the final outcome will be decided by the logic inside the fragments programmatically.
* The final outcome fragment computes the result dynamically.
* The result will depend on stored state values.

Hardcoded navigation to different endings is **not allowed** in the nav.xml needs to be removed.
### Task 3: Store Decisions in ViewModel
Every meaningful decision made by the user must in every fragment, needs to be stored in the ViewModel object:
* Update the shared ViewModel.
* Not rely solely on Safe Args, Safe args will still need to be implemented.
* Not rely on UI text values.
* Not be stored in local variables.

Safe Args will be used for screen-to-screen data transfer, but they are not a replacement for persistent state.
Safe Args will then eventually contribute to making the shared view model more accurate.

### Task 4: Compute Final Outcome Dynamically
Inside the final outcome fragment:
* Read values from the ViewModel.
* Determine the mission result based on stored decisions.
* Display appropriate text depending on the computed logic.

The final screen must behave differently depending on:
* selectedStrategy
* helicopterDismissed
* trustLevel

### Task 5: Handle Screen Rotation Correctly
Every fragment must display the trust level (an integer) that is defined in the Shared View model.
Rotate the device during:
* Level 1
* Level 2
* Final Outcome

Display Trust Level in Every Fragment, inside each fragment’s onViewCreated():
```
trustTextView.text = "Trust Level: ${viewModel.trustLevel}"
```

The application must:
* Not reset mission decisions.
* Not restart the navigation flow.
* Not lose stored values.
