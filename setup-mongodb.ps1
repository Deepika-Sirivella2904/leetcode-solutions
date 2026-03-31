# MongoDB Setup Script for Windows
# Run this script as Administrator

Write-Host "🔧 MongoDB Setup Script" -ForegroundColor Cyan
Write-Host "========================" -ForegroundColor Cyan

# Check if MongoDB is installed
Write-Host "📋 Checking MongoDB installation..." -ForegroundColor Yellow

$mongoPath = Get-Command mongod -ErrorAction SilentlyContinue
if ($mongoPath) {
    Write-Host "✅ MongoDB is installed at: $($mongoPath.Source)" -ForegroundColor Green
} else {
    Write-Host "❌ MongoDB is not installed or not in PATH" -ForegroundColor Red
    Write-Host "Please download and install MongoDB from: https://www.mongodb.com/try/download/community" -ForegroundColor Yellow
    Write-Host "Then run this script again." -ForegroundColor Yellow
    Read-Host "Press Enter to exit"
    exit
}

# Check MongoDB service
Write-Host "`n🔍 Checking MongoDB service status..." -ForegroundColor Yellow

$service = Get-Service -Name "MongoDB" -ErrorAction SilentlyContinue
if ($service) {
    Write-Host "MongoDB service found: $($service.Name)" -ForegroundColor Green
    Write-Host "Current status: $($service.Status)" -ForegroundColor Green
    
    if ($service.Status -ne "Running") {
        Write-Host "🚀 Starting MongoDB service..." -ForegroundColor Yellow
        Start-Service -Name "MongoDB" -ErrorAction Stop
        Write-Host "✅ MongoDB service started successfully!" -ForegroundColor Green
    } else {
        Write-Host "✅ MongoDB service is already running!" -ForegroundColor Green
    }
} else {
    Write-Host "❌ MongoDB service not found" -ForegroundColor Red
    Write-Host "You may need to reinstall MongoDB as a service" -ForegroundColor Yellow
}

# Check if MongoDB is listening on port 27017
Write-Host "`n🔍 Checking MongoDB connection on port 27017..." -ForegroundColor Yellow

$port = 27017
$connection = Test-NetConnection -ComputerName localhost -Port $port -WarningAction SilentlyContinue

if ($connection.TcpTestSucceeded) {
    Write-Host "✅ MongoDB is listening on port $port!" -ForegroundColor Green
} else {
    Write-Host "❌ MongoDB is not listening on port $port" -ForegroundColor Red
    Write-Host "The service might not be running correctly" -ForegroundColor Yellow
}

# Create data directory if it doesn't exist
Write-Host "`n📁 Checking MongoDB data directory..." -ForegroundColor Yellow

$dataPath = "C:\data\db"
if (-not (Test-Path $dataPath)) {
    Write-Host "Creating MongoDB data directory: $dataPath" -ForegroundColor Yellow
    New-Item -ItemType Directory -Path $dataPath -Force | Out-Null
    Write-Host "✅ Data directory created!" -ForegroundColor Green
} else {
    Write-Host "✅ Data directory exists!" -ForegroundColor Green
}

# Test MongoDB connection using mongosh
Write-Host "`n🧪 Testing MongoDB connection..." -ForegroundColor Yellow

try {
    $mongosh = Get-Command mongosh -ErrorAction SilentlyContinue
    if ($mongosh) {
        Write-Host "Using mongosh to test connection..." -ForegroundColor Yellow
        $result = & mongosh --eval "db.runCommand({ping: 1})" --quiet
        if ($LASTEXITCODE -eq 0) {
            Write-Host "✅ MongoDB connection test successful!" -ForegroundColor Green
        } else {
            Write-Host "❌ MongoDB connection test failed" -ForegroundColor Red
        }
    } else {
        # Try with mongo (older version)
        $mongo = Get-Command mongo -ErrorAction SilentlyContinue
        if ($mongo) {
            Write-Host "Using mongo to test connection..." -ForegroundColor Yellow
            $result = & mongo --eval "db.runCommand({ping: 1})" --quiet
            if ($LASTEXITCODE -eq 0) {
                Write-Host "✅ MongoDB connection test successful!" -ForegroundColor Green
            } else {
                Write-Host "❌ MongoDB connection test failed" -ForegroundColor Red
            }
        } else {
            Write-Host "⚠️  Neither mongosh nor mongo found in PATH" -ForegroundColor Yellow
        }
    }
} catch {
    Write-Host "❌ Error testing MongoDB connection: $($_.Exception.Message)" -ForegroundColor Red
}

# Show useful commands
Write-Host "`n📋 Useful MongoDB Commands:" -ForegroundColor Cyan
Write-Host "==========================" -ForegroundColor Cyan
Write-Host "Start MongoDB service:" -ForegroundColor White
Write-Host "  net start MongoDB" -ForegroundColor Gray
Write-Host ""
Write-Host "Stop MongoDB service:" -ForegroundColor White
Write-Host "  net stop MongoDB" -ForegroundColor Gray
Write-Host ""
Write-Host "Check service status:" -ForegroundColor White
Write-Host "  sc query MongoDB" -ForegroundColor Gray
Write-Host ""
Write-Host "Check if MongoDB is running:" -ForegroundColor White
Write-Host "  tasklist | findstr mongod" -ForegroundColor Gray
Write-Host ""
Write-Host "Open MongoDB Compass (GUI):" -ForegroundColor White
Write-Host "  mongodb://localhost:27017" -ForegroundColor Gray

Write-Host "`n🎉 MongoDB setup complete!" -ForegroundColor Green
Write-Host "You can now start your Node.js application with: npm start" -ForegroundColor Green

Read-Host "`nPress Enter to exit"
