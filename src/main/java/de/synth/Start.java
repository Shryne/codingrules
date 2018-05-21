package de.synth;

import org.takes.Request;
import org.takes.Response;
import org.takes.Take;
import org.takes.facets.fork.FkRegex;
import org.takes.facets.fork.TkFork;
import org.takes.http.Exit;
import org.takes.http.FtBasic;
import org.takes.rs.RsHtml;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Start {
    public static void main(final String... args) throws Exception {
        new FtBasic(
                new TkFork(
                        new FkRegex("/robots\\.txt", ""),
                        new FkRegex("/", new TkIndex())
                ),
                8080
        ).start(Exit.NEVER);
    }

    static class TkIndex implements Take {
        @Override
        public Response act(final Request req) throws IOException {
            return new RsHtml(
                    new String(Files.readAllBytes(Paths.get("site.html")), StandardCharsets.UTF_8)
            );
        }
    }
}
