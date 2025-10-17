🔗 Linkora – The Connectathon Sprint App

Linkora is a complete, production-ready Android app built in just 3 days for The Connectathon Sprint challenge.
It’s a smart, swipe-based connection app that helps users explore profiles, filter by interests, and build meaningful connections — all within a beautiful Material UI.

🚀 Approach

The app is built using the MVVM (Model–View–ViewModel) architecture to maintain clean separation between UI and business logic.

Model Layer: Defines the data (Profile model) and handles database interactions through Room entities and DAOs.

Repository Layer: Manages all data operations and provides a clean API for the ViewModel.

ViewModel Layer: Handles business logic and exposes LiveData to the UI.

View Layer (Activities): Displays data, manages user actions (swipe, connect, skip), and updates views reactively.

Focus areas:

Smooth animations and transitions

Persistent local data storage

Simple and intuitive user experience

🧰 Tools & Frameworks Used

Language: Kotlin

Architecture: MVVM

Local Database: Room Persistence Library

UI Components: RecyclerView, CardView, Material Components

Animations: XML-based transitions (slide-in / slide-out)

Design: Vector Drawables, Shape Drawables, Material Design 3

✨ Features Implemented

✅ Swipeable Profiles – Browse profiles with smooth card transitions
✅ Connect / Skip Actions – Interact with intuitive buttons and feedback toasts
✅ Smart Filtering – Filter profiles by domain or interests via dropdown menu
✅ Persistent Storage – Room database retains your connections locally
✅ Smooth Animations – Enhanced UX through slide-in/out effects
✅ Modern Material UI – Styled with cohesive theme and typography

🎨 Color Theme
| Role         | Color     | Description   |
| ------------ | --------- | ------------- |
| Primary      | `#3E0703` | Deep maroon   |
| Primary Dark | `#660B05` | Darker maroon |
| Accent       | `#8C1007` | Bright red    |
| Background   | `#FFF0C4` | Warm cream    |

📁 Project Structure
app/
├── manifests/
│   └── AndroidManifest.xml
│
├── kotlin+java/
│   └── com/example/linkora/
│       ├── data/
│       │   ├── AppDatabase.kt          # Room database instance
│       │   ├── Converters.kt           # Type converters for complex data
│       │   ├── Profile.kt              # Data model (Entity)
│       │   ├── ProfileDao.kt           # DAO interface for CRUD operations
│       │   └── ProfileRepository.kt    # Repository for data handling
│       │
│       ├── ConnectedActivity.kt        # Displays all connected profiles
│       ├── ConnectedAdapter.kt         # RecyclerView adapter with DiffUtil
│       ├── MainActivity.kt             # Swipe-based main interface
│       └── MainViewModel.kt            # ViewModel for business logic
│
│       ├── (androidTest)/              # Instrumented tests
│       │   └── ExampleInstrumentedTest.kt
│       └── (test)/                     # Unit tests
│           └── ExampleUnitTest.kt
│
├── res/
│   ├── anim/
│   │   ├── slide_in_right.xml
│   │   └── slide_out_left.xml
│   │
│   ├── drawable/
│   │   ├── circle_background.xml
│   │   ├── ic_connect.xml
│   │   ├── ic_connection.xml
│   │   ├── ic_empty.xml
│   │   └── other vector assets
│   │
│   ├── layout/                         # Layout XMLs for activities
│   ├── values/                         # colors.xml, strings.xml, themes.xml
│   └── mipmap/                         # App launcher icons
│
└── java (generated)                    # Auto-generated files


🧩 Pending / Future Enhancements

🔜 Profile image upload (camera or gallery)
🔜 Real-time connection sync via Firebase Firestore
🔜 Smart swipe recommendations powered by AI/ML
🔜 In-app messaging or chat feature

⏱️ Time Spent

Total development time: ~3 days
