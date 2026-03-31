# LeetCode Solutions

This repository contains my daily LeetCode problem solutions. Each solution is organized by date and problem name for easy reference.

## 📁 Directory Structure

```
leetcode-solutions/
├── templates/           # Solution templates for different languages
│   ├── python-template.py
│   ├── javascript-template.js
│   └── java-template.java
├── scripts/            # Automation scripts
│   └── add-solution.ps1
└── YYYY-MM-DD-Problem-Name/  # Individual problem solutions
    └── solution.{ext}
```

## 🚀 Quick Start

### Using the Automation Script (Recommended)

1. **Add a new solution:**
   ```powershell
   .\leetcode-solutions\scripts\add-solution.ps1 -ProblemName "Two Sum" -ProblemLink "https://leetcode.com/problems/two-sum/" -Difficulty "Easy" -Language "python" -MethodName "twoSum"
   ```

2. **Parameters:**
   - `-ProblemName`: Name of the LeetCode problem
   - `-ProblemLink`: URL to the problem
   - `-Difficulty`: Easy, Medium, or Hard
   - `-Language`: python, javascript, or java
   - `-MethodName`: The main method name for the solution
   - `-Description` (optional): Brief description of your approach
   - `-TimeComplexity` (optional): Time complexity analysis
   - `-SpaceComplexity` (optional): Space complexity analysis

3. **Complete your solution:**
   - Navigate to the created directory
   - Implement your solution in the generated file
   - Test your implementation

4. **Commit and push:**
   ```bash
   git add .
   git commit -m "Add solution for [Problem Name]"
   git push origin main
   ```

### Manual Process

1. Create a new directory: `YYYY-MM-DD-Problem-Name`
2. Copy the appropriate template from `templates/`
3. Rename it to `solution.{ext}`
4. Fill in the placeholders and implement your solution

## 📋 Solution Template

Each solution includes:
- Problem metadata (name, link, difficulty, date)
- Time and space complexity analysis
- Brief description of the approach
- Test cases for verification

## 🏆 Progress Tracking

- **Total Problems Solved:** [Update this number]
- **Easy:** [Count]
- **Medium:** [Count] 
- **Hard:** [Count]

## 📚 Languages Used

- **Python** 🐍
- **JavaScript** 🟨
- **Java** ☕

## 🤝 Contributing

This is a personal repository for tracking my LeetCode progress. Feel free to use the templates and scripts for your own practice!

## 📞 Contact

- **GitHub:** [Deepika-Sirivella2904](https://github.com/Deepika-Sirivella2904)

---

**Happy Coding!** 💻✨
