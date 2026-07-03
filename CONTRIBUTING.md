# Contributing to Phoenix

First off, thank you for taking the time to contribute. Contributions of all
kinds are welcome: bug reports, feature requests, documentation, and code.

By participating in this project, you agree to abide by our
[Code of Conduct](CODE_OF_CONDUCT.md).

---

## Reporting Bugs

Before opening an issue, please:

1. Search [existing issues](../../issues) to avoid duplicates.
2. Include clear reproduction steps, the platform (Android or iOS), and relevant
   logs or stack traces.
3. Mention your environment: OS, JDK version, Android Studio or Xcode version.

## Suggesting Features

Open an issue describing the feature, the problem it solves, and, if possible,
a rough idea of how it might work. Discussion first saves everyone time.

---

## Development Setup

1. Fork and clone the repo.
2. Follow the [Getting Started](README.md#getting-started) guide to configure
   Firebase and `local.properties`.
3. Make sure the project builds before you start:
   ```bash
   ./gradlew :androidApp:assembleDebug
   ```

## Pull Request Workflow

1. **Create a branch** off `main`:
   ```bash
   git checkout -b feat/short-description
   ```
2. **Make your changes.** Keep them focused, one logical change per PR.
3. **Follow the code style:**
   - Use the standard [Kotlin coding conventions](https://kotlinlang.org/docs/coding-conventions.html).
   - Keep shared logic in `commonMain`; use `expect`/`actual` only when a
     platform API is genuinely required.
   - Prefer the version catalog (`gradle/libs.versions.toml`) for all
     dependency and version changes.
4. **Build and test locally:**
   ```bash
   ./gradlew build
   ./gradlew :composeApp:allTests
   ```
5. **Never commit secrets.** `google-services.json` and
   `GoogleService-Info.plist` are git-ignored. Do not force-add them.
6. **Write a clear PR description:** what changed, why, and how to test it.
   Link any related issues (e.g. `Closes #123`).

### Commit Messages

Use clear, imperative commit messages. [Conventional Commits](https://www.conventionalcommits.org)
are encouraged but not required:

```
feat: add image compression before upload
fix: resolve infinite loader on upload failure
docs: update Firebase setup steps
```

---

## Code Review

- All PRs require at least one approving review before merge.
- Be responsive to feedback and keep the discussion respectful.
- Maintainers may request changes or squash commits on merge.

## License

By contributing, you agree that your contributions will be licensed under the
[MIT License](LICENSE) that covers the project.

---

Thanks again, and happy hacking.
