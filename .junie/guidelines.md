# MagazineNews Style Guidelines

This document outlines the coding standards and style guidelines to follow when making changes to the MagazineNews application.

## Java Code Style

### Naming Conventions
- **Classes**: Use PascalCase (e.g., `NewsAdapter`, `ApiInterface`)
- **Methods**: Use camelCase (e.g., `fetchNews()`, `bindViewHolder()`)
- **Variables**: Use camelCase (e.g., `newsItem`, `recyclerView`)
- **Constants**: Use UPPER_SNAKE_CASE (e.g., `API_BASE_URL`, `DEFAULT_TIMEOUT`)
- **Resource IDs**: Use snake_case (e.g., `item_news`, `text_title`)

### Code Organization
- Keep methods short and focused on a single responsibility
- Organize classes logically with related methods grouped together
- Use meaningful names that describe what the code does
- Add comments for complex logic, but prefer self-documenting code

### Architecture
- Follow MVVM (Model-View-ViewModel) architecture
- Use interfaces for dependency injection
- Separate concerns: UI logic in ViewModels, data access in repositories
- Use LiveData or RxJava for reactive programming

## XML Layout Style

### Layout Organization
- Use ConstraintLayout for complex UIs
- Keep layouts flat (avoid deep nesting)
- Extract styles for reused attributes
- Use dimensions resources instead of hardcoded values

### Resource Naming
- Layouts: `activity_*.xml`, `fragment_*.xml`, `item_*.xml`
- Drawables: `ic_*` for icons, `bg_*` for backgrounds
- Colors: Use descriptive names like `color_primary` instead of `blue`
- Dimensions: Use descriptive names like `text_size_headline` instead of `large_text`

## Kotlin Code Style (for future Kotlin implementation)

### Kotlin Specific
- Use property access syntax instead of getters/setters
- Use string templates instead of concatenation
- Use expression bodies for simple functions
- Use data classes for model objects
- Use extension functions to extend functionality

## General Guidelines

### Documentation
- Add KDoc/JavaDoc comments for public methods and classes
- Include parameter descriptions and return value information
- Document non-obvious behavior

### Testing
- Write unit tests for all business logic
- Use descriptive test method names that explain the scenario and expected outcome
- Follow the Arrange-Act-Assert pattern in tests

### Version Control
- Write clear, concise commit messages
- Keep commits focused on a single change
- Reference issue numbers in commit messages when applicable

### Performance
- Avoid unnecessary object creation
- Use ViewHolder pattern for RecyclerViews
- Optimize image loading with proper caching
- Avoid blocking the main thread with long operations