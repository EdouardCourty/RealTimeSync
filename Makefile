move-to-dev:
	cp target/RealTimeSync-1.0.0.jar dev/plugins

start-dev-server:
	java -Xmx512m -jar dev/paper_1.12.2.jar nogui
