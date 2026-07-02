# DeepSeek for PyCharm

Use [DeepSeek AI](https://chat.deepseek.com) directly inside PyCharm — no API key required.
This plugin embeds DeepSeek's web interface as a tool window and lets you send code
snippets from the editor with a single right-click.

---

## Features

- **Embedded chat** — DeepSeek web interface as a native PyCharm tool window
  (View → Tool Windows → DeepSeek)
- **Send code instantly** — Select code in the editor, right-click, and choose
  "Send to DeepSeek" to paste it into the chat input
- **Theme sync** — Automatically matches PyCharm's light/dark theme
- **No API key** — Works with your free DeepSeek account via the web interface

## Screenshot

![screenshot-placeholder](docs/screenshot.png)

## Installation

### From disk (any PyCharm)

1. Download the latest `.zip` from
   [Releases](https://github.com/haoli-00/deepseek-pycharm-plugin/releases)
2. Open PyCharm → **Settings** → **Plugins** → ⚙ → **Install Plugin from Disk...**
3. Select the downloaded ZIP and restart PyCharm

### From JetBrains Marketplace (once published)

1. Open PyCharm → **Settings** → **Plugins** → **Marketplace**
2. Search "DeepSeek for PyCharm"
3. Click **Install** and restart

## Usage

1. Open the tool window: **View → Tool Windows → DeepSeek**
2. Log in to your DeepSeek account in the embedded browser
3. In the editor, select a code snippet
4. Right-click → **Send to DeepSeek** (or use the default shortcut)

## Building from source

```bash
# Prerequisites: JDK 17+
git clone https://github.com/haoli-00/deepseek-pycharm-plugin.git
cd deepseek-pycharm-plugin
./gradlew buildPlugin
```

The plugin ZIP will be at `build/distributions/DeepSeek-for-PyCharm-*.zip`.

## Compatibility

| Plugin version | PyCharm min | PyCharm max |
|---------------|-------------|-------------|
| 1.0.x         | 2023.2      | 2025.2+     |

## License

This project is licensed under the MIT License — see the [LICENSE](LICENSE) file.

## Disclaimer

This project is not affiliated with DeepSeek. "DeepSeek" is a trademark of
其 / Deep Seek (company). This plugin simply provides a convenient interface to
the publicly available web service.
