package com.scottsdaleair.utils;

import com.google.gson.Gson;
import com.scottsdaleair.data.Tuple;
import com.scottsdaleair.utils.config.Config;
import com.scottsdaleair.utils.config.ConfigStatus;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Configurator {
  private static Gson gson = new Gson();
  private static HashMap<String, Tuple<Config, ConfigStatus>> confs = new HashMap<>();
  private static String configExt = ".posc";
  private static String confPath = "./conf/";


  /**
   * Loads the given config type into the Configurator. If the config has already
   * been loaded, this does nothing.
   *
   * @param <T>      Type of config class
   * @param confType Type of the config to load
   * @throws IOException If the confi file does not exist on disk
   */
  public static <T extends Config> void loadConfig(Class<T> confType) throws IOException {
    String configName = nameFromType(confType);
    if (!isLoaded(configName)) {
      String json = Files.readString(Paths.get(confPath + configName + configExt));
      T newconf = gson.fromJson(json, confType);
      confs.put(configName, new Tuple<Config, ConfigStatus>(newconf, new ConfigStatus()));
    }
  }

  /**
   * Saves the given config type to disk. If the given config has not been
   * changed, this does nothing.
   *
   * @param <T>      Type of config class
   * @param confType Type of the config to load
   * @throws IOException If the config cannot be saved to disk
   */
  public static <T extends Config> void saveConfig(Class<T> confType) throws IOException {
    String configName = nameFromType(confType);
    Tuple<Config, ConfigStatus> tup = confs.get(configName);
    if (tup.t1.isDirty()) {
      Config conf = tup.t0;
      saveConfig(conf, nameFromType(confType));
      tup.t1.clean();
      confs.put(configName, tup);
    }
  }

  /**
   * Saves all currently loaded config files that have been changed.
   *
   * @throws IOException If any of the configs cannot be saved to disk
   */
  public static void saveAll() throws IOException {
    for (String key : confs.keySet()) {
      Tuple<Config, ConfigStatus> tup = confs.get(key);
      if (tup.t1.isDirty()) {
        saveConfig(tup.t0, key);
        tup.t1.clean();
        confs.put(key, tup);
      }
    }
  }

  /**
   * Returns the loaded config object for the given type. If the config is not
   * loaded, this returns null.
   *
   * @param <T>        Type of config class
   * @param configType Type of config to get
   * @return The config object of the given type, or null if not loaded.
   */
  public static <T extends Config> T getConfig(Class<T> configType) {
    String configName = nameFromType(configType);
    if (isLoaded(configName)) {
      @SuppressWarnings("unchecked")
      T retConf = (T) confs.get(nameFromType(configType)).t0;
      return retConf;
    }
    return null;
  }

  /**
   * Updates the config object in memory of the given type.
   * @param <T>         Type of config class
   * @param config      Config object to update
   * @param configType  Type of config to update
   */
  public static <T extends Config> void updateConfig(T config, Class<T> configType) {
    String configName = nameFromType(configType);
    Tuple<Config, ConfigStatus> tup = confs.get(nameFromType(configType));
    tup.t0 = config;
    tup.t1.dirtify();
    confs.put(configName, tup);
  }

  /**
   * Updates the given config object and saves it to disk.
   * This is equivelant to calling:
   *<br/>
   * {@code
   *  updateConfig(config, ConfigType.class);
   *  saveConfig(ConfigType.class);
   * }
   * @param <T>           Type of config class
   * @param config        Config object to update
   * @param configType    Type of config to update
   * @throws IOException  If the config cannot be saved to disk
   */
  public static <T extends Config> void updateAndSave(T config, Class<T> configType) throws IOException {
    updateConfig(config, configType);
    saveConfig(configType);
  }

  private static boolean isLoaded(String configName) {
    return confs.get(configName) != null;
  }

  private static void saveConfig(Config conf, String filename) throws IOException {
    if (conf != null) {
      String json = gson.toJson(conf);
      Files.writeString(Paths.get(confPath + filename + configExt), json);
      // isDirty = false;
    }
  }

  private static <T> String nameFromType(Class<T> type) {
    return type.getSimpleName().toLowerCase();
  }
}
