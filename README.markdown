### SEEK Android team coding task ###

There are issues with the `Display Job Details on a new screen` commit on the main branch.

Task 1:
Using a production quality lens, review the `Display Job Details on a new screen` commit
as if it were a Pull Request on a separate branch and identify as many issues as you can. 
What are the issues and why? How would you improve it and why?

ANSWER: After reviewing the original implementation, I found several areas where the code could be improved to meet production-quality standards. Here's a breakdown:

## What’s not working well:

1. Tight coupling in ViewModel - Retrofit and the API call logic are written directly inside the ViewModel. This breaks separation of concerns and makes testing harder.

2. No Dependency Injection - Dependencies like Retrofit are manually created in the ViewModel. Instead, we should use Koin (already part of the project) to inject them properly.

3. Unsafe use of GlobalScope - The use of GlobalScope.launch in the ViewModel can lead to memory leaks. Coroutine calls should be wrapped with viewModelScope or safeLaunch.

4. No error handling - Failures like network errors or unexpected responses are not handled. This can cause crashes or poor user feedback.

5. Missing UI state management - There’s no clear way to handle loading, success, or error states. A sealed class like JobDetailsUIState would be ideal for managing this cleanly.

6. No mapping between layers - The API model is used directly in the UI. Ideally, we should map it to a domain model using a mapper class.

7. No previews or reusable UI components - Loading and error views are not modularized, making testing and previewing harder.

8. No test coverage - Because of the way dependencies are wired and logic is written, the code is not testable. Injecting usecases and mocking them would allow proper unit testing.

## What I’d improve:

- Move API logic to a RemoteDataSource

- Introduce a proper usecase and repository

- Use Koin for all DI, just like how used in SearchResult feature

- Add proper error handling and UI states

- Use coroutines safely with viewModelScope

- Add a mapper to separate API and domain models

- Modularize UI into composable views with previews

- Make the code testable and add basic unit tests using MockK and Junit



Task 2:
Refactor the code and create a branch (as if it were a new PR), implementing the solution in a better way.
You can choose to reuse as much or as little of the existing code as you want without any limitations.
For example, you can create a new branch on top of the latest commit on main to fix the issues,
or you can reimplement the solution from scratch by branching off an earlier commit.
Explain your solution and why it's better.

When complete, please zip up your solution (include the .git folder) and send it back to us.
DO NOT share the coding task publicly, or upload it as a public github repository.

ANSWER: 

## What I changed and why:
1. Introduced Clean Architecture Layers (Please note, all my code changes reflect improvement of Job Details feature only for this MVP implementation)

✅ Moved API logic to JobDetailsRemoteDataSource and its Impl.

✅ Repository only fetches data; no transformation logic inside.

✅ Usecase now includes mapping logic using a JobDetailsDtoToDomainMapper.

2. Added Dependency Injection using Koin

✅ Retrofit client and API interface are injected via Koin modules.

✅ ViewModel uses constructor injection, improving testability and separation of concerns.

3. Handled Errors Gracefully

✅ Added a DomainException and ErrorReason structure to handle common error types.

✅ ViewModel exposes state via a sealed JobDetailsUIState (Loading, Success, Error).

4. Used Coroutines and Flow Properly

✅ RemoteDataSource emits data using Flow with proper Dispatchers.IO.

✅ ViewModel uses safeLaunch to collect Flow and update state.

5. Refactored UI with Stateless Composables

✅ Created JobDetailsScreenContent, JobDetailsLoadingView, JobDetailsErrorView, and JobDetailsSuccessContent composables.

✅ Each Composable is previewable and reusable.

✅ UI state is driven by collectAsStateWithLifecycle.


6. Added Logging and Debugging Tools

✅ Integrated Timber and Chucker for API debugging and structured logs.

7. Added Unit Tests

✅ Wrote unit tests for ViewModel, UseCase, Mapper.

✅ Used Mockk, UnconfinedTestDispatcher, and runTest from kotlinx.coroutines.test.

## Why this solution is better:
- More modular & scalable – Follows clean separation of layers (data, domain, UI).

- Highly testable – Each layer is independently testable.

- Production-grade error handling – Covers nullability and failure states clearly.

- Composable UI – Easy to preview, test, and extend.

- Industry-standard tools – Koin for DI, Flow for reactive streams, and structured logging/debugging.
