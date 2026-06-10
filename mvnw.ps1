param([Parameter(ValueFromRemainingArguments=$true)] $args)
$ErrorActionPreference = 'Stop'
$scriptDir = Split-Path -Parent $MyInvocation.MyCommand.Definition
$wrapperDir = Join-Path $scriptDir ".mvn\wrapper"
$mvnVersion = '3.9.4'
$mvndir = Join-Path $wrapperDir "apache-maven-$mvnVersion"
$mvnexe = Join-Path $mvndir "bin\mvn.cmd"
if (!(Test-Path $mvnexe)) {
    Write-Host "Maven $mvnVersion not found locally; downloading..."
    $zip = Join-Path $wrapperDir "apache-maven-$mvnVersion-bin.zip"
    if (!(Test-Path $wrapperDir)) { New-Item -ItemType Directory -Path $wrapperDir | Out-Null }
    $urls = @(
        "https://dlcdn.apache.org/maven/maven-3/$mvnVersion/binaries/apache-maven-$mvnVersion-bin.zip",
        "https://downloads.apache.org/maven/maven-3/$mvnVersion/binaries/apache-maven-$mvnVersion-bin.zip",
        "https://archive.apache.org/dist/maven/maven-3/$mvnVersion/binaries/apache-maven-$mvnVersion-bin.zip"
    )
    $downloaded = $false
    foreach ($url in $urls) {
        try {
            Write-Host "Trying $url"
            Invoke-WebRequest -Uri $url -OutFile $zip -UseBasicParsing -TimeoutSec 120
            $downloaded = $true
            break
        } catch {
            Write-Warning "Failed to download from ${url}: $($_.Exception.Message)"
        }
    }
    if (-not $downloaded) { Write-Error "Failed to download Maven from known mirrors."; exit 2 }
    Write-Host "Extracting $zip"
    Expand-Archive -Path $zip -DestinationPath $wrapperDir -Force
    Remove-Item $zip -Force
}
if (!(Test-Path $mvnexe)) { Write-Error "Maven executable not found after download."; exit 1 }
& $mvnexe @args
exit $LASTEXITCODE
