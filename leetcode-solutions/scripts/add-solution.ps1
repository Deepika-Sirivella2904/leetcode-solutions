param(
    [Parameter(Mandatory=$true)]
    [string]$ProblemName,
    
    [Parameter(Mandatory=$true)]
    [string]$ProblemLink,
    
    [Parameter(Mandatory=$true)]
    [ValidateSet("Easy", "Medium", "Hard")]
    [string]$Difficulty,
    
    [Parameter(Mandatory=$true)]
    [ValidateSet("python", "javascript", "java")]
    [string]$Language,
    
    [Parameter(Mandatory=$true)]
    [string]$MethodName,
    
    [string]$Description = "",
    [string]$TimeComplexity = "",
    [string]$SpaceComplexity = ""
)

# Get current date
$Date = Get-Date -Format "yyyy-MM-dd"

# Create problem directory
$ProblemDir = "leetcode-solutions\$Date-$($ProblemName -replace '[^a-zA-Z0-9]', '-')"
if (!(Test-Path $ProblemDir)) {
    New-Item -ItemType Directory -Path $ProblemDir -Force
}

# Select template
$TemplateFile = switch ($Language) {
    "python" { "leetcode-solutions\templates\python-template.py" }
    "javascript" { "leetcode-solutions\templates\javascript-template.js" }
    "java" { "leetcode-solutions\templates\java-template.java" }
    default { throw "Unsupported language: $Language" }
}

# Read template
$Template = Get-Content $TemplateFile -Raw

# Replace placeholders
$Content = $Template -replace '\{\{PROBLEM_NAME\}\}', $ProblemName `
                     -replace '\{\{PROBLEM_LINK\}\}', $ProblemLink `
                     -replace '\{\{DIFFICULTY\}\}', $Difficulty `
                     -replace '\{\{DATE\}\}', $Date `
                     -replace '\{\{METHOD_NAME\}\}', $MethodName `
                     -replace '\{\{DESCRIPTION\}\}', $Description `
                     -replace '\{\{TIME_COMPLEXITY\}\}', $TimeComplexity `
                     -replace '\{\{SPACE_COMPLEXITY\}\}', $SpaceComplexity

# Determine file extension
$Extension = switch ($Language) {
    "python" { ".py" }
    "javascript" { ".js" }
    "java" { ".java" }
    default { throw "Unsupported language: $Language" }
}

# Create solution file
$SolutionFile = "$ProblemDir\solution$Extension"
$Content | Out-File -FilePath $SolutionFile -Encoding UTF8

Write-Host "✅ Solution created successfully!"
Write-Host "📁 Directory: $ProblemDir"
Write-Host "📄 File: $SolutionFile"
Write-Host ""
Write-Host "Next steps:"
Write-Host "1. Add your solution code to $SolutionFile"
Write-Host "2. Test your solution"
Write-Host "3. Commit and push to GitHub:"
Write-Host "   git add $SolutionFile"
Write-Host "   git commit -m 'Add solution for $ProblemName'"
Write-Host "   git push origin main"
