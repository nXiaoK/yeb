package com.xiaokw.server.util;

import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


public class FastDFSUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(FastDFSUtils.class);

    /**
     * 初始化客户端
     */
    static {
        try {
            String filePath = new ClassPathResource("fdfs_client.conf").getFile().getAbsolutePath();
            ClientGlobal.init(filePath);
        } catch (Exception e) {
            LOGGER.error("初始化FastDFS失败");
            e.printStackTrace();
        }
    }

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    public static String[] upload(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        LOGGER.info("文件名:" + fileName);
        StorageClient storageClient = null;
        String[] uploadResults = null;
        try {
            storageClient = getStorageClient();
            uploadResults = storageClient.upload_file(file.getBytes(), fileName.substring(fileName.lastIndexOf(".") + 1), null);

        } catch (Exception e) {
            LOGGER.error("上传文件失败", e.getMessage());
        }
        if (uploadResults == null) {
            LOGGER.error("上传文件失败", storageClient.getErrorCode());
        }
        return uploadResults;
    }

    /**
     * 生成Storage客户端
     *
     * @return
     */
    private static StorageClient getStorageClient() throws IOException {
        TrackerServer trackerServer = getTrackerServer();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        return storageClient;
    }

    /**
     * 获取文件信息
     *
     * @param groupName
     * @param remoteFileName
     * @return
     */
    public static FileInfo getFileInfo(String groupName, String remoteFileName) {
        StorageClient storageClient = null;

        try {
            storageClient = getStorageClient();
            return storageClient.get_file_info(groupName, remoteFileName);
        } catch (Exception e) {
            LOGGER.error("获取文件失败", e.getMessage());
        }
        return null;
    }

    /**
     * 获取文件路径
     *
     * @return
     */
    public static String getTrackUrl() {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = null;
        StorageServer storeStorage = null;
        try {
            trackerServer = trackerClient.getTrackerServer();
            storeStorage = trackerClient.getStoreStorage(trackerServer);
        } catch (Exception e) {
            LOGGER.error("文件路径获取失败", e.getMessage());
        }
        return "http://" + storeStorage.getInetSocketAddress().getHostString() + ":8888/";
    }

    /**
     * 下载文件
     *
     * @param groupName
     * @param remoteFileName
     * @return
     */
    public static InputStream downFile(String groupName, String remoteFileName) {
        StorageClient storageClient = null;

        try {
            storageClient = getStorageClient();
            byte[] bytes = storageClient.download_file(groupName, remoteFileName);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            return byteArrayInputStream;
        } catch (Exception e) {
            LOGGER.error("文件下载失败", e.getMessage());
        }
        return null;
    }

    /**
     * 删除文件
     *
     * @param groupName
     * @param remoteFileName
     */
    public static void deleteFile(String groupName, String remoteFileName) {
        StorageClient storageClient = null;

        try {
            storageClient = getStorageClient();
            storageClient.delete_file(groupName, remoteFileName);
        } catch (Exception e) {
            LOGGER.error("文件删除失败", e.getMessage());
        }
    }


    /**
     * 生成tracker服务器
     *
     * @return
     * @throws IOException
     */
    private static TrackerServer getTrackerServer() throws IOException {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getTrackerServer();
        return trackerServer;
    }
}
