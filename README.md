![UltraWarps Banner](https://cdn.modrinth.com/data/cached_images/23f96d391f5bb00d9be641ef1a739f8988358bc7_0.webp)
# UltraWarps

UltraWarps is a modern, high-performance server warp system built for Paper, Purpur, and Folia.  
Designed for admin-controlled server warps, it provides safe, smooth, and fully configurable teleportation with proper warmup handling and cancellation.

---

## ✨ Features

### ⚡ Performance Focused
- Async warp loading and saving
- Thread-safe teleport scheduling
- Folia-supported
- No main-thread blocking operations

### ⏳ Teleport Warmup System
- Configurable countdown timer
- Title-based countdown display
- Instant cancellation on movement
- Cancel title + chat message
- Success title + chat message

### 🎨 Fully Configurable Messages
- Custom headers
- Custom titles & subtitles
- Placeholder support (`%warp%`, `%seconds%`)
- Clean formatting system

### 🔐 Admin-Controlled Warps
- `/uw set <name>`
- `/uw delete <name>`
- `/warp <name>`
- `/listwarps`

No player-created warps — only server-defined locations.

### 🛠 Admin Utilities
- `/uw reload` (shows reload time in ms)
- `/uw info` (version, total warps, storage type)
- `/uw help`

### 💾 Storage Options
- YAML storage (default)
- JSON-ready structure
- Async saving during runtime
- Safe synchronous save on shutdown

---

## 📜 Commands

### Player Commands

| Command | Description |
|----------|-------------|
| `/warp <name>` | Teleport to a server warp |
| `/listwarps` | View available warps |

### Admin Commands

| Command | Description |
|----------|-------------|
| `/uw set <name>` | Create a warp at your location |
| `/uw delete <name>` | Delete a warp |
| `/uw reload` | Reload configuration |
| `/uw info` | View plugin information |
| `/uw help` | View command list |

Alias:
/uw


---

## 🔑 Permissions

| Permission | Description | Default |
|------------|------------|----------|
| `ultrawarps.use` | Use `/warp` | true |
| `ultrawarps.list` | Use `/listwarps` | true |
| `ultrawarps.admin` | Access admin commands | op |

---

## 🧩 Compatibility

- Paper 1.20+
- Purpur
- Folia
- Java 17+

---

## 📦 Installation

1. Download `UltraWarps.jar`
2. Place it inside your `/plugins` folder
3. Restart your server
4. Configure `config.yml`
5. Start creating warps

---

## 🎯 Design Goals

UltraWarps is built with:
- Clean architecture
- Maintainability
- Performance
- Modern API usage
- No legacy hacks