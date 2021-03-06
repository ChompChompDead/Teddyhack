// imports and package name
package com.chompchompdead.teddyware.client;

import com.chompchompdead.teddyware.api.config.ClickGUILoad;
import com.chompchompdead.teddyware.api.config.ClickGUISave;
import com.chompchompdead.teddyware.api.config.ConfigStop;
import com.chompchompdead.teddyware.api.util.font.FontUtil;
import com.chompchompdead.teddyware.client.command.CommandManager;
import com.chompchompdead.teddyware.client.gui.clickgui.ClickGUIScreen;
import com.chompchompdead.teddyware.client.module.ModuleManager;
import com.chompchompdead.teddyware.api.config.Config;
import com.chompchompdead.teddyware.api.event.EventProcessor;
import com.chompchompdead.teddyware.client.setting.SettingManager;
import me.zero.alpine.EventBus;
import me.zero.alpine.EventManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

// set mod variables and client class
@Mod(modid = Teddyware.MODID, name = Teddyware.NAME, version = Teddyware.VERSION)
public class Teddyware {
    public static final String MODID = "teddyware";
    public static final String NAME = "Teddyware";
    public static final String VERSION = "0.32";
    public static final Logger log = LogManager.getLogger(NAME + " v" + VERSION);
    public static final EventBus EVENT_BUS = new EventManager();

    public static ModuleManager moduleManager;
    public static SettingManager settingManager;
    public static Config config;
    public static ClickGUISave clickGUISave;
    public static ClickGUILoad clickGUILoad;
    public static CommandManager commandManager;
    public static ClickGUIScreen clickGUIScreen;
    public static FontUtil fontManager;
    public static EventProcessor eventProcessor;

    @Instance //instances
    public static Teddyware instance = new Teddyware();

    @EventHandler
    public void PreInit(FMLPreInitializationEvent event) { }

    @EventHandler
    public void Init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(instance);
        MinecraftForge.EVENT_BUS.register(this);

        // register stuff
        MinecraftForge.EVENT_BUS.register(new ModuleManager());
        moduleManager = new ModuleManager();
        log.info("module manager is ready");

        eventProcessor = new EventProcessor();
        log.info("events are ready");

        settingManager = new SettingManager();
        log.info("settings are ready");

        commandManager = new CommandManager();
        log.info("commands are ready");

        config = new Config();
        log.info("configs are ready");

        clickGUIScreen = new ClickGUIScreen();
        log.info("clickgui is ready");

        clickGUISave = new ClickGUISave();
        clickGUILoad = new ClickGUILoad();
        Runtime.getRuntime().addShutdownHook(new ConfigStop());
        log.info("clickGUI saves and loads are ready");

        fontManager = new FontUtil();
        log.info("custom font is ready");

        log.info(NAME + " is done loading!");
        Discord.start();
        Display.setTitle(NAME + " | v" + VERSION);
    }

    @EventHandler
    public void PostInit(FMLPostInitializationEvent event) {

    }

    public String getName() { return NAME; }
}
