/*
 * Copyright (C) 2018 University of South Florida (sjbarbeau@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.usf.cutr.transitfeedqualitycalculator.downloaders;

import edu.usf.cutr.transitfeedqualitycalculator.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * A class that defines common actions for a downloader of GTFS feeds but leaves some implementation to extending classes
 * for downloading feeds from specific sources
 */
public abstract class BaseDownloader {

    private Path mPath;

    /**
     * Constructs the specified downloader, writing the downloaded files to the provided Path
     *
     * @param path   path in which to write the output files
     */
    BaseDownloader(Path path) throws IOException {
        mPath = path;
        Files.createDirectories(mPath);
    }

    /**
     * Downloads GTFS feeds from the source specified in the extending classes
     * @throws IOException
     */
    abstract public void downloadFeeds() throws IOException;

    /**
     * Writes the GTFS or GTFS-realtime feed at the provided feedUrl to the provided folderName and fileName
     * @param feedUrl full URL (including any API key) to the provided GTFS or GTFS-realtime file
     * @param folderName
     * @param fileName
     * @throws IOException
     */
    protected void writeFeedToFile(URL feedUrl, String folderName, String fileName) throws IOException {
        String fullPath = mPath.resolve(folderName) + File.separator + fileName;

        Files.createDirectories(mPath.resolve(folderName));
        FileUtil.writeUrlToFile(feedUrl, fullPath);
    }
}
