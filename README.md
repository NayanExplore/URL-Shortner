# LinkSnap - Full-Stack URL Shortener Application

LinkSnap is a modern, full-stack, secure URL Shortener application. It features a React frontend powered by Vite and Tailwind CSS, and a Java backend powered by Spring Boot, Spring Security (JWT-secured), and PostgreSQL. 

The application allows users to register, log in, shorten long URLs, manage their custom links in a personal dashboard, and view detailed click analytics over time via interactive charts.

---

## 🚀 Key Features

*   **Secure Authentication**: Secure sign-up and login utilizing Spring Security, BCrypt password hashing, and stateless JWT tokens.
*   **URL Shortening**: Instantly convert long URLs into clean, short links (e.g., `http://localhost:5173/QN7XOa0a`).
*   **Personal Dashboard**: Authenticated users can view, copy, and manage their generated short links.
*   **Advanced Analytics**: Track click events over time. Features interactive date-range filtering and line graphs displaying click frequency trends.
*   **Automatic Redirection**: Accessing the short link automatically records the click event (with timestamps) and redirects the visitor to the destination website.
*   **Docker Containerization**: Entire stack configured to run out-of-the-box using Docker and Docker Compose.

---

## 🛠️ Technology Stack

### Frontend
*   **Core**: React 18, Vite (for fast development/builds)
*   **Styling**: Tailwind CSS & Material UI (MUI v6)
*   **Routing & State**: React Router DOM v7 & React Query (v3)
*   **Interactions & Charts**: Framer Motion, React Icons, Chart.js & React-Chartjs-2
*   **Form Management**: React Hook Form

### Backend
*   **Core**: Spring Boot 3.4, Java 21
*   **Security**: Spring Security & JSON Web Token (JWT)
*   **Data Access**: Spring Data JPA & Hibernate
*   **Databases**: PostgreSQL (Production/Docker), H2 (Local In-Memory)
*   **Utilities**: Project Lombok

---

## 📂 Project Structure

```text
url-shortener-project-main/
├── url-shortener-project-main/
│   ├── url-shortener-react/    # React + Vite Frontend
│   │   ├── src/                # Frontend source code
│   │   ├── Dockerfile          # Frontend container definition
│   │   └── package.json        # Frontend dependencies & scripts
│   │
│   ├── url-shortener-sb/       # Spring Boot Backend
│   │   ├── src/                # Java source code
│   │   ├── Dockerfile          # Backend container definition
│   │   └── pom.xml             # Maven dependencies
│   │
│   └── docker-compose.yml      # Multi-container orchestrator
└── README.md                   # Main Project Documentation
```

---

## ⚙️ Setting Up & Running the Project

You can run this project using either Docker Compose (recommended) or locally by starting the frontend and backend services separately.

### Option 1: Running with Docker Compose (Recommended)

Make sure you have [Docker](https://www.docker.com/) and **Docker Compose** installed.

1. Navigate to the inner project directory:
   ```bash
   cd url-shortener-project-main
   ```
2. Build and start all services (PostgreSQL, Spring Boot Backend, and React Frontend):
   ```bash
   docker-compose up --build
   ```
3. Once running, open your browser to:
   *   **Frontend**: `http://localhost:5173`
   *   **Backend API**: `http://localhost:8080`
   *   **PostgreSQL Port**: `5432`

---

### Option 2: Running Locally (Without Docker)

#### 1. Backend Setup (Spring Boot)
1. Navigate to the backend directory:
   ```bash
   cd url-shortener-project-main/url-shortener-sb
   ```
2. By default, the application is configured to run with an in-memory **H2 Database** when started locally.
3. Launch the Spring Boot application using Maven:
   *   **Windows**:
       ```cmd
       mvnw.cmd spring-boot:run
       ```
   *   **macOS / Linux**:
       ```bash
       ./mvnw spring-boot:run
       ```
4. The backend server will start on port `8080`.

#### 2. Frontend Setup (React)
1. Open a new terminal and navigate to the frontend directory:
   ```bash
   cd url-shortener-project-main/url-shortener-react
   ```
2. Install npm dependencies:
   ```bash
   npm install
   ```
3. Copy/configure environmental variables (if needed). The default `.env` points to `http://localhost:8080`.
4. Run the frontend development server:
   ```bash
   npm run dev
   ```
5. Open `http://localhost:5173` in your browser.

---

## 🔌 API Documentation

### Authentication (`/api/auth`)
*   `POST /api/auth/public/register` — Register a new user.
    *   **Body**: `{ "username": "...", "email": "...", "password": "..." }`
*   `POST /api/auth/public/login` — Login to receive a JWT Token.
    *   **Body**: `{ "username": "...", "password": "..." }`
    *   **Response**: Returns the JWT token.

### URL Mapping (`/api/urls`) — *Requires JWT Authorization header*
*   `POST /api/urls/shorten` — Create a shortened URL.
    *   **Body**: `{ "originalUrl": "https://example.com" }`
*   `GET /api/urls/myurls` — List all URLs created by the authenticated user.
*   `GET /api/urls/analytics/{shortUrl}` — Fetch detailed click-event analytics for a short URL.
    *   **Parameters**: `startDate` and `endDate` (formatted as ISO Local Date Time).
*   `GET /api/urls/totalClicks` — Fetch daily summary clicks count for a user.
    *   **Parameters**: `startDate` and `endDate` (formatted as ISO Local Date).

### Redirection (Unauthenticated)
*   `GET /{shortUrl}` — Redirect to the target long URL. Automatically tracks click metadata.

---

## 🛡️ License & Usage Policies
The resources and roadmap files included are for learning purposes. Refer to the internal documentation for usage terms.